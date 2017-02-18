package com.marvin.bundle.swing.resources.view;

import com.marvin.bundle.framework.mvc.view.IView;
import java.awt.Container;
import java.util.HashMap;
import javax.swing.Action;

public abstract class AbstractSwingView implements IView<Action, Container> {
    
    private Boolean initialized = Boolean.FALSE;
    
    protected void init(HashMap<String, ?> model, Container pane) {
        if(this.initialized.equals(Boolean.TRUE)) {
            return;
        }
        
        prepare(model, pane);
        
        this.initialized = Boolean.TRUE;
    }
    
    abstract protected void prepare(HashMap<String, ?> model, Container pane);
    
    abstract protected void display(HashMap<String, ?> model, Container pane);

    @Override
    public void render(HashMap<String, ?> model, Action request, Container root) throws Exception {
        init(model, root);
        display(model, root);
    }
}
