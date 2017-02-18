package com.marvin.bundle.swing;

import com.marvin.bundle.framework.handler.Handler;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JOptionPane;

public class ApplicationAction extends AbstractAction {

    private static final Logger LOG = Logger.getLogger(ApplicationAction.class.getName());
    
    private Handler handler;
    private Container pane;

    public ApplicationAction(String name, String command, Handler handler, Container pane) {
        this(name, command, null, handler, pane);
    }

    public ApplicationAction(String name, String command, Icon icon, Handler handler, Container pane) {
        super(name, icon);
        this.handler = handler;
        this.pane = pane;
        putValue(Action.ACTION_COMMAND_KEY, command);
    }

    @Override
    public final void putValue(String key, Object newValue) {
        super.putValue(key, newValue);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        LOG.log(Level.INFO, "Performing an action {0}", getValue(Action.ACTION_COMMAND_KEY));
        try {
            this.handler.handle(this, pane, true);
            this.pane.doLayout();
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, ex.getMessage());
            JOptionPane.showMessageDialog((Component) pane, ex.getMessage());
        }
    }
}
