package com.marvin.bundle.swing.controller;

import com.marvin.bundle.framework.mvc.controller.ContainerControllerResolver;
import com.marvin.bundle.framework.mvc.controller.ControllerNameParser;
import com.marvin.bundle.framework.mvc.controller.ControllerReference;
import javax.swing.Action;

public class ActionControllerResolver extends ContainerControllerResolver<Action> {

    public ActionControllerResolver(ControllerNameParser parser) {
        super(parser);
    }
    
    @Override
    public ControllerReference resolve(Action action) throws Exception {
        Object controller = action.getValue("_controller");
        return castController(controller);
    }

}
