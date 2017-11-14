package com.marvin.bundle.swing.resources.view;

import com.marvin.bundle.framework.mvc.Handler;
import com.marvin.bundle.swing.action.ApplicationAction;
import com.marvin.component.mvc.model.Model;
import com.marvin.component.mvc.view.View;
import java.awt.Color;
import java.awt.Dimension;
import java.util.Map;
import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class SwingView extends View<Action, JFrame> {

    private Boolean initialized = Boolean.FALSE;
    private JPanel panel;
    
    public SwingView(String name) {
        super(name);
    }

    @Override
    public void load() throws Exception {
    }
    
    protected ApplicationAction createAction(String name, String command, Handler<Action, JFrame> handler, JFrame frame) {
        ApplicationAction action = new ApplicationAction(name, command, handler, frame);
        return action;
    }
    
    protected void init(Handler<Action, JFrame> handler, Model model, JFrame frame) throws Exception {
//        if(this.initialized.equals(Boolean.TRUE)) {
//            return;
//        }
        this.getPanel().removeAll();
        prepare(handler, model, frame);
        
//        this.initialized = Boolean.TRUE;
    }

    protected void prepare(Handler<Action, JFrame> handler, Model model, JFrame frame) throws Exception {
    };
    
    protected void display(Model model, JFrame frame) {
//        this.getPanel().repaint();
//        this.getPanel().doLayout();
        frame.getContentPane().add(getPanel());
        frame.getContentPane().repaint();
        frame.getContentPane().doLayout();
        frame.repaint();
        frame.doLayout();
    }

    @Override
    public void render(Handler<Action, JFrame> handler, Map<String, Object> model, Action request, JFrame frame) throws Exception {
        Model m = new Model(model);
        init(handler, m, frame);
        display(m, frame);
        this.getPanel().repaint();
        this.getPanel().doLayout();
    }
    
    public SwingView add(JComponent component) {
        getPanel().add(component);
        return this;
    }
    
    public SwingView background(Color color) {
        getPanel().setBackground(color);
        return this;
    }
     
    public SwingView size(int w, int h) {
        getPanel().setSize(w, h);
        return this;
    }
    
    public SwingView size(Dimension dimension) {
        getPanel().setSize(dimension);
        return this;
    }
    
    public JPanel getPanel() {
        if (panel == null) {
            panel = new JPanel();
        }
        return panel;
    }

    public void setPanel(JPanel panel) {
        this.panel = panel;
    }
}
