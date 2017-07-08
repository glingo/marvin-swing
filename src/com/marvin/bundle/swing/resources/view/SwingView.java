package com.marvin.bundle.swing.resources.view;

import com.marvin.bundle.framework.mvc.Handler;
import java.util.HashMap;
import java.util.Map;
import javax.swing.Action;
import javax.swing.JFrame;

public class SwingView extends AbstractSwingView {

    public SwingView(String name) {
        super(name);
    }

    @Override
    protected void prepare(HashMap<String, Object> model, JFrame frame) throws Exception  {
        
    }

    @Override
    public void render(Handler<Action, JFrame> handler, Map<String, Object> model, Action request, JFrame response) throws Exception {
    }
}
