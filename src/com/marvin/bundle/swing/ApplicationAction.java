/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marvin.bundle.swing;

import java.awt.event.ActionEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.Icon;

public class ApplicationAction extends AbstractAction {
    
    ActionHandler handler;
    
    public ApplicationAction(String name, ActionHandler handler) {
        super(name);
        this.handler = handler;
    }

    public ApplicationAction(String name, Icon icon, ActionHandler handler) {
        super(name, icon);
        this.handler = handler;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            handler.handle(this);
        } catch (Exception ex) {
            Logger.getLogger(ApplicationAction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
