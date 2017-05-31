package com.marvin.bundle.swing.view;

import com.marvin.bundle.swing.view.xml.XmlViewReader;
import com.marvin.bundle.framework.mvc.Handler;
import com.marvin.component.mvc.view.View;
import java.util.HashMap;
import javax.swing.Action;
import javax.swing.JFrame;

public class SwingView extends View<Action, JFrame> {

    public SwingView(String name) {
        super(name);
    }

    @Override
    public void load() throws Exception {
    }

    @Override
    public void render(Handler<Action, JFrame> handler, HashMap<String, Object> model, Action request, JFrame response) throws Exception {

    }
    
}
