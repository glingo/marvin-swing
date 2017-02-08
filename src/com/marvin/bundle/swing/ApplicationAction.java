package com.marvin.bundle.swing;

import com.marvin.bundle.framework.handler.Handler;
import java.awt.Component;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JOptionPane;
import javax.swing.JRootPane;
import javax.swing.SwingUtilities;

public class ApplicationAction extends AbstractAction {

    private static final Logger LOG = Logger.getLogger(ApplicationAction.class.getName());

    private Handler<Action, JRootPane> handler;

    public ApplicationAction(String name, String command, Handler handler) {
        this(name, command, null, handler);
    }

    public ApplicationAction(String name, String command, Icon icon, Handler handler) {
        super(name, icon);
        this.handler = handler;
        putValue(Action.ACTION_COMMAND_KEY, command);
    }

    @Override
    public final void putValue(String key, Object newValue) {
        super.putValue(key, newValue);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        LOG.log(Level.INFO, "Performing an action {0}", getValue(Action.ACTION_COMMAND_KEY));
        Object source = e.getSource();

        if (!(source instanceof Component)) {
            return;
        }
        
        Component c = (Component) e.getSource();

        JRootPane pane = SwingUtilities.getRootPane(c);
        try {
            this.handler.handle(this, pane, true);
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, ex.getMessage());
            JOptionPane.showMessageDialog(pane, ex.getMessage());
        }
    }
}
