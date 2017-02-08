package com.marvin.bundle.swing.controller;

import com.marvin.component.kernel.controller.ContainerControllerResolver;
import com.marvin.component.kernel.controller.ControllerNameParser;
import com.marvin.component.kernel.controller.ControllerReference;
import javax.swing.Action;

public class ActionControllerResolver extends ContainerControllerResolver<Action> {

    public ActionControllerResolver(ControllerNameParser parser) {
        super(parser);
    }
    
    @Override
    public ControllerReference resolveController(Action action) throws Exception {
        Object controller = action.getValue("_controller");
        return castController(controller);
    }
}
