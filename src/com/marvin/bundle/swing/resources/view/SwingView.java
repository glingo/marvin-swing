package com.marvin.bundle.swing.resources.view;

import com.marvin.bundle.framework.handler.Handler;
import java.awt.Container;
import java.util.HashMap;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class SwingView extends AbstractSwingView {

    public SwingView(Handler handler) {
        super(handler, new JPanel());
    }

    @Override
    protected void prepare(HashMap<String, ?> model, JFrame frame) throws Exception  {
    }
    
}
