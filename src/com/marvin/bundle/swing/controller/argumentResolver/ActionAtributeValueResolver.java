package com.marvin.bundle.swing.controller.argumentResolver;

import com.marvin.component.kernel.controller.ArgumentMetadata;
import com.marvin.component.kernel.controller.ArgumentValueResolverInterface;
import javax.swing.Action;

public class ActionAtributeValueResolver implements ArgumentValueResolverInterface<Action>  {

    @Override
    public boolean support(Action action, ArgumentMetadata argument) {
        return !argument.isIsVariadic() && action.getValue(argument.getName()) != null;
    }

    @Override
    public Object resolve(Action action, ArgumentMetadata argument) {
        return action.getValue(argument.getName());
    }
    
}