package com.marvin.bundle.swing;

import com.marvin.bundle.framework.handler.Handler;
import com.marvin.bundle.swing.utils.SwingUtils;
import com.marvin.component.container.IContainer;
import com.marvin.component.container.exception.ContainerException;
import com.marvin.component.kernel.Kernel;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public abstract class Application {

    protected final Logger logger = Logger.getLogger(getClass().getName());
    
    private final Kernel kernel;
    
    private JFrame frame;
    private Container pane;
    private Boolean ready;

    protected Application(Kernel kernel) {
        this.kernel = kernel;
    }
    
    protected abstract JMenuBar createMenu();
    
    public void initialize() {
        this.frame = new JFrame();
        this.pane = frame.getContentPane();
        
        this.frame.setJMenuBar(createMenu());
        
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
        SwingUtils.show(this.frame);
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
        try {
            return getContainer().get("action_handler", Handler.class);
        }catch(ContainerException ce) {
            throw new RuntimeException("Could not load application.");
        }
    }
    
    public ApplicationAction createAction(String name, String command) {
        return new ApplicationAction(name, command, getHandler());
    }
    
    public void fireAction(ApplicationAction action, Container pane) {
        action.actionPerformed(new ActionEvent(pane, 0, "/"));
    }
}
