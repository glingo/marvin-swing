/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marvin.bundle.swing;

import java.awt.event.ActionEvent;
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
        handler.handle(this);
    }
    
}
