package com.marvin.bundle.swing.utils;

import com.marvin.bundle.swing.Application;
import com.marvin.component.kernel.Kernel;
import java.awt.Window;
import java.beans.Beans;
import java.lang.reflect.Constructor;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public abstract class SwingUtils {
    private static final Logger LOG = Logger.getLogger(SwingUtils.class.getName());

    private SwingUtils() {
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
    
    public static synchronized <T extends Application> void launch(final Class<T> applicationClass, Kernel kernel) {
        
	SwingUtilities.invokeLater(() -> {
            try {
                Application application = create(applicationClass, kernel);
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
                LOG.log(Level.WARNING, s, e);
            }
        }

        return app;
    }
}
