package com.marvin.bundle.swing.controller.argumentResolver;

import com.marvin.component.mvc.controller.argument.ArgumentMetadata;
import com.marvin.component.mvc.controller.argument.ArgumentValueResolverInterface;
import javax.swing.Action;

public class ActionAtributeValueResolver implements ArgumentValueResolverInterface<Action, Object>  {

    @Override
    public boolean support(Action action, Object response, ArgumentMetadata argument) {
        return !argument.isVariadic() && action.getValue(argument.getName()) != null;
    }

    @Override
    public Object resolve(Action action, Object response, ArgumentMetadata argument) {
        return action.getValue(argument.getName());
    }

}
