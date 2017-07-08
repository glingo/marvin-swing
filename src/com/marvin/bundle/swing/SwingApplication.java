package com.marvin.bundle.swing;

import com.marvin.bundle.framework.Application;
import com.marvin.bundle.swing.action.ApplicationAction;
import com.marvin.component.kernel.Kernel;
import java.awt.Container;
import java.awt.Window;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.SwingUtilities;

public abstract class SwingApplication extends Application {

    private JFrame frame;
    private Container pane;

    public SwingApplication(String environment, boolean debug) {
        super(environment, debug);
    }
    
    protected abstract JMenuBar createMenu(JMenuBar menuBar);
    
    @Override
    public void startup() {
        this.frame = new JFrame();
        this.pane = frame.getContentPane();
        
        this.frame.setJMenuBar(createMenu(new JMenuBar()));
//        this.frame.setJMenuBar(new JMenuBar());
        
	this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.frame.setLocationRelativeTo(null);
        super.startup();
    }
    
    @Override
    public void waitForReady() {
        // dispatch home action event to display home view
        ApplicationAction homeAction = createAction("home_action", "/");
        fireAction(homeAction, this.pane);
        ready();
    }
    
    @Override
    public void ready() {
        show(this.frame);
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
    
//    public static synchronized <T extends Application> void launch(final Class<T> applicationClass, String[] args) {
//	SwingUtilities.invokeLater(() -> Application.launch(applicationClass, args));
//    }
    
//    public static <T extends Application> T create(Class<T> applicationClass, Kernel kernel) throws Exception {
//
//        if (!Beans.isDesignTime()) {
//            /* A common mistake for privileged applications that make
//             * network requests (and aren't applets or web started) is to
//             * not configure the http.proxyHost/Port system properties.
//             * We paper over that issue here.
//             */
//            try {
//                System.setProperty("java.net.useSystemProxies", "true"); 
//            } catch (SecurityException ignoreException) {
//                // Unsigned apps can't set this property. 
//            }
//        }
//
//        /* Construct the Application object.  The following
//         * complications, relative to just calling
//         * applicationClass.newInstance(), allow a privileged app to
//         * have a private static inner Application subclass.
//         */
//        Constructor<T> ctor = applicationClass.getDeclaredConstructor(new Class[]{Kernel.class});
//        if (!ctor.isAccessible()) {
//            try {
//                ctor.setAccessible(true);
//            } catch (SecurityException ignore) {
//                // ctor.newInstance() will throw an IllegalAccessException
//            }
//        }
//        
//        kernel.boot();
//        
//        T app = ctor.newInstance(kernel);
//        
//        if (!Beans.isDesignTime()) {
//            /* Initialize the UIManager lookAndFeel property with the
//             * Application.lookAndFeel resource.  If the the resource
//             * isn't defined we default to "system".
//             */
//            String key = "Application.lookAndFeel";
//            String lnf = kernel.getContainer().getParameterWithDefault(key, "system", String.class);
//            try {
//                if (lnf.equalsIgnoreCase("system")) {
//                    String name = UIManager.getSystemLookAndFeelClassName();
//                    UIManager.setLookAndFeel(name);
//                }else if (!lnf.equalsIgnoreCase("default")) {
//                    UIManager.setLookAndFeel(lnf);
//                }
//            } catch(ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
//                String s = "Couldn't set LookandFeel " + key + " = \"" + lnf + "\"";
//                logger.log(Level.WARNING, s, e);
//            }
//        }
//
//        return app;
//    }
}
