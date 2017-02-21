package com.marvin.bundle.swing.action;

import com.marvin.bundle.framework.handler.Handler;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;

public class SubmitAction extends ApplicationAction {
    
    public SubmitAction(String name, String command, Handler handler, JFrame frame) {
        super(name, command, handler, frame);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        super.actionPerformed(e);
    }
}
