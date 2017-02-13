package com.marvin.bundle.swing.resources.view;

import com.marvin.bundle.framework.mvc.view.IView;
import java.awt.Container;
import java.util.HashMap;
import javax.swing.Action;
import javax.swing.JRootPane;

public abstract class AbstractSwingView implements IView<Action, JRootPane> {
    
    private Boolean initialized = Boolean.FALSE;
    
    protected void init() {
        if(this.initialized.equals(Boolean.TRUE)) {
            return;
        }
        
        prepare();
        
        this.initialized = Boolean.TRUE;
    }
    
    abstract protected void prepare();
    
    abstract protected void display(HashMap<String, ?> model, Container pane);

    @Override
    public void render(HashMap<String, ?> model, Action request, JRootPane root) throws Exception {
        Container pane = root.getContentPane();
        
        init();
        display(model, pane);
    }
    
}
