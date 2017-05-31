package com.marvin.bundle.swing.action;

import com.marvin.bundle.framework.mvc.Handler;
import java.awt.event.ActionEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ApplicationAction extends AbstractAction {

    private static final Logger LOG = Logger.getLogger(ApplicationAction.class.getName());
    
    private Handler handler;
    private JFrame frame;

    public ApplicationAction(String name, String command, Handler handler, JFrame frame) {
        this(name, command, null, handler, frame);
    }

    public ApplicationAction(String name, String command, Icon icon, Handler handler, JFrame frame) {
        super(name, icon);
        this.handler = handler;
        this.frame = frame;
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
            this.handler.handle(this, this.frame, true);
        } catch (Exception ex) {
            ex.printStackTrace();
            LOG.log(Level.SEVERE, ex.getMessage());
            JOptionPane.showMessageDialog(this.frame, ex.getMessage());
        }
    }
}
