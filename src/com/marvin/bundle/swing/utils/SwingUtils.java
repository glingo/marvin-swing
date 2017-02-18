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

}
