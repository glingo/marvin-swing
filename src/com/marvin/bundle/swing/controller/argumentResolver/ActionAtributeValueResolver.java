package com.marvin.bundle.swing.controller.argumentResolver;

import com.marvin.component.kernel.controller.argument.ArgumentMetadata;
import com.marvin.component.kernel.controller.argument.ArgumentValueResolverInterface;
import javax.swing.Action;

public class ActionAtributeValueResolver implements ArgumentValueResolverInterface<Action, Object>  {

    @Override
    public boolean support(Action action, Object response, ArgumentMetadata argument) {
        return !argument.isIsVariadic() && action.getValue(argument.getName()) != null;
    }

    @Override
    public Object resolve(Action action, Object response, ArgumentMetadata argument) {
        return action.getValue(argument.getName());
    }

}
