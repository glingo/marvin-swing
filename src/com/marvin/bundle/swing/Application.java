package com.marvin.bundle.swing;

import com.marvin.bundle.swing.action.ApplicationAction;
import com.marvin.bundle.framework.handler.Handler;
import com.marvin.component.container.IContainer;
import com.marvin.component.kernel.Kernel;
import java.awt.Container;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.beans.Beans;
import java.lang.reflect.Constructor;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public abstract class Application {

    protected static final Logger logger = Logger.getLogger(Application.class.getName());
    
    private final Kernel kernel;
    
    private static Application application = null;
    
    private JFrame frame;
    private Container pane;
    private Boolean ready;

    protected Application(Kernel kernel) {
        this.kernel = kernel;
    }
    
    protected abstract JMenuBar createMenu(JMenuBar menuBar);
    
    public void initialize() {
        this.frame = new JFrame();
        this.pane = frame.getContentPane();
        
        this.frame.setJMenuBar(createMenu(new JMenuBar()));
//        this.frame.setJMenuBar(new JMenuBar());
        
	this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.frame.setLocationRelativeTo(null);
    }
    
    public void startup() {
        // dispatch home action event to display home view
        ApplicationAction homeAction = createAction("home_action", "/");
        fireAction(homeAction, this.pane);
        ready();
    }
    
    public void ready() {
        show(this.frame);
        this.ready = true;
    }
    
    public void shutdown() {}
    
    public final void exit() {}
    
    public void end() {
        Runtime.getRuntime().exit(0);
    }
    
    public void waitForReady() {};

    public IContainer getContainer(){
        return this.kernel.getContainer();
    }
    
    public Handler getHandler() {
        return getContainer().get("action_handler", Handler.class);
    }
    
    public ApplicationAction createAction(String name, String command) {
        ApplicationAction action = new ApplicationAction(name, command, getHandler(), getFrame());
        return action;
    }
    
    public void fireAction(ApplicationAction action, Container pane) {
        action.actionPerformed(new ActionEvent(pane, 0, "/"));
    }

    public Container getPane() {
        return pane;
    }

    public JFrame getFrame() {
        return frame;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    public void setPane(Container pane) {
        this.pane = pane;
    }
    
    public static void show(Window window) {
        if (window == null) {
            return;
        }

        window.pack();
        window.setVisible(true);
    }

    public static void hide(Window window) {
        if (window == null) {
            return;
        }

        window.setVisible(false);
    }
    
    public static synchronized <T extends Application> T getInstance(Class<T> applicationClass, Kernel kernel) {
        if (application == null) {
            try {
                application = create(applicationClass, kernel);
            } catch (Exception e) {
                String msg = String.format("Couldn't construct %s", applicationClass);
                throw(new Error(msg, e));
            }
        }
	return applicationClass.cast(application);
    }
    
    public static synchronized <T extends Application> void launch(final Class<T> applicationClass, Kernel kernel) {
        
	SwingUtilities.invokeLater(() -> {
            try {
                application = create(applicationClass, kernel);
                application.initialize();
                application.startup();
                application.waitForReady();
            } catch (Exception e) {
                String msg = String.format("Application %s failed to launch", applicationClass);
                throw(new Error(msg, e));
            }
        });
    }
    
    public static <T extends Application> T create(Class<T> applicationClass, Kernel kernel) throws Exception {

        if (!Beans.isDesignTime()) {
            /* A common mistake for privileged applications that make
             * network requests (and aren't applets or web started) is to
             * not configure the http.proxyHost/Port system properties.
             * We paper over that issue here.
             */
            try {
                System.setProperty("java.net.useSystemProxies", "true"); 
            } catch (SecurityException ignoreException) {
                // Unsigned apps can't set this property. 
            }
        }

        /* Construct the Application object.  The following
         * complications, relative to just calling
         * applicationClass.newInstance(), allow a privileged app to
         * have a private static inner Application subclass.
         */
        Constructor<T> ctor = applicationClass.getDeclaredConstructor(new Class[]{Kernel.class});
        if (!ctor.isAccessible()) {
            try {
                ctor.setAccessible(true);
            } catch (SecurityException ignore) {
                // ctor.newInstance() will throw an IllegalAccessException
            }
        }
        
        kernel.boot();
        
        T app = ctor.newInstance(kernel);
        
        if (!Beans.isDesignTime()) {
            /* Initialize the UIManager lookAndFeel property with the
             * Application.lookAndFeel resource.  If the the resource
             * isn't defined we default to "system".
             */
            String key = "Application.lookAndFeel";
            String lnf = kernel.getContainer().getParameterWithDefault(key, "system", String.class);
            try {
                if (lnf.equalsIgnoreCase("system")) {
                    String name = UIManager.getSystemLookAndFeelClassName();
                    UIManager.setLookAndFeel(name);
                }else if (!lnf.equalsIgnoreCase("default")) {
                    UIManager.setLookAndFeel(lnf);
                }
            } catch(ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
                String s = "Couldn't set LookandFeel " + key + " = \"" + lnf + "\"";
                logger.log(Level.WARNING, s, e);
            }
        }

        return app;
    }
}
