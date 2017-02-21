package com.marvin.bundle.swing.resources.view;

import com.marvin.bundle.framework.handler.Handler;
import com.marvin.bundle.framework.mvc.view.IView;
import java.awt.Container;
import java.util.HashMap;
import javax.swing.Action;
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
    
    protected void init(HashMap<String, ?> model, JFrame frame) throws Exception {
        if(this.initialized.equals(Boolean.TRUE)) {
            return;
        }
        
        prepare(model, frame);
        
        this.initialized = Boolean.TRUE;
    }
    
    abstract protected void prepare(HashMap<String, ?> model, JFrame frmae) throws Exception ;
    
    protected void display(HashMap<String, ?> model, JFrame frame) {
        this.getPanel().repaint();
        this.getPanel().doLayout();
        frame.getContentPane().add(this.getPanel());
        
        frame.getContentPane().repaint();
        frame.getContentPane().doLayout();
    }

    @Override
    public void render(HashMap<String, ?> model, Action request, JFrame frame) throws Exception {
        init(model, frame);
        display(model, frame);
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
