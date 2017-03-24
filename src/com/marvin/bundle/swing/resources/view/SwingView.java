package com.marvin.bundle.swing.resources.view;

import com.marvin.bundle.framework.handler.Handler;
import java.util.HashMap;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class SwingView extends AbstractSwingView {

    public SwingView(Handler handler) {
        super(handler, new JPanel());
    }
    
    public SwingView(Handler handler, JPanel panel) {
        super(handler, panel);
    }

    @Override
    protected void prepare(HashMap<String, Object> model, JFrame frame) throws Exception  {
        
    }
}
