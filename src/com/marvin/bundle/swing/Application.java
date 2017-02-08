package com.marvin.bundle.swing;

import com.marvin.component.kernel.Kernel;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.WindowConstants;

public abstract class MarvinSwingApplication {
    
    private final Kernel kernel;
    private JFrame frame;
    private JMenuBar menuBar;
    private JMenu menu;

    public MarvinSwingApplication(Kernel kernel) {
        this.kernel = kernel;
    }
    
    protected abstract void buildFrame(JFrame frame);
    protected abstract JMenuBar buildMenuBar();
    
    public ActionHandler getHandler(){
        System.out.println(this.kernel.getContainer());
        return this.kernel.getContainer().get("action_handler", ActionHandler.class);
    }
    
    public JMenuBar getMenuBar(){
        if(this.menuBar == null) {
            this.menuBar = this.buildMenuBar();
        }
        return this.menuBar;
    }
    
    public void show() {
        this.kernel.boot();
        
        if(this.frame == null) {
            this.frame = new JFrame();
            this.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            this.frame.setPreferredSize(new Dimension(1200, 800));
//            this.frame.setSize(new Dimension(1200, 800));
            this.buildFrame(this.frame);
        }
        
        this.frame.setJMenuBar(getMenuBar());
        this.frame.pack();
        this.frame.setVisible(true);
    }
    
}
