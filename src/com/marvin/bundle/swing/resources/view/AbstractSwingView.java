package com.marvin.bundle.swing.resources.view;

import com.marvin.bundle.framework.handler.Handler;
import com.marvin.bundle.framework.mvc.view.IView;
import com.marvin.bundle.swing.action.ApplicationAction;
import java.awt.Color;
import java.awt.Dimension;
import java.util.HashMap;
import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

public abstract class AbstractSwingView implements IView<Action, JFrame> {
    
    private Boolean initialized = Boolean.FALSE;
    
    private final Handler handler;
    private JPanel panel;

    public AbstractSwingView(Handler handler, JPanel panel) {
        this.handler = handler;
        this.panel = panel;
    }
    
    protected void init(HashMap<String, Object> model, JFrame frame) throws Exception {
//        if(this.initialized.equals(Boolean.TRUE)) {
//            return;
//        }
        this.getPanel().removeAll();
        prepare(model, frame);
        
//        this.initialized = Boolean.TRUE;
    }
    
    abstract protected void prepare(HashMap<String, Object> model, JFrame frmae) throws Exception ;
    
    protected void display(HashMap<String, Object> model, JFrame frame) {
//        this.getPanel().repaint();
//        this.getPanel().doLayout();
        frame.getContentPane().add(getPanel());
        frame.getContentPane().repaint();
        frame.getContentPane().doLayout();
        frame.repaint();
        frame.doLayout();
    }

    @Override
    public void render(HashMap<String, Object> model, Action request, JFrame frame) throws Exception {
        init(model, frame);
        display(model, frame);
        this.getPanel().repaint();
        this.getPanel().doLayout();
    }
    
    protected ApplicationAction createAction(String name, String command, JFrame frame) {
        return new ApplicationAction(name, command, getHandler(), frame);
    }
    
    public AbstractSwingView add(JComponent component) {
        getPanel().add(component);
        return this;
    }
    
    public AbstractSwingView background(Color color) {
        getPanel().setBackground(color);
        return this;
    }
     
    public AbstractSwingView size(int w, int h) {
        getPanel().setSize(w, h);
        return this;
    }
    
    public AbstractSwingView size(Dimension dimension) {
        getPanel().setSize(dimension);
        return this;
    }
    
    public Handler getHandler() {
        return handler;
    }

    public JPanel getPanel() {
        return panel;
    }

    public void setPanel(JPanel panel) {
        this.panel = panel;
    }
}
