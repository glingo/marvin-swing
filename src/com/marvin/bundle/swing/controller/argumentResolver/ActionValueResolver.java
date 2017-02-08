package com.marvin.bundle.swing.controller.argumentResolver;

import com.marvin.component.kernel.controller.argument.ArgumentMetadata;
import com.marvin.component.kernel.controller.argument.ArgumentValueResolverInterface;
import com.marvin.component.util.ClassUtils;
import javax.swing.Action;

public class ActionValueResolver implements ArgumentValueResolverInterface<Action, Object>  {

    @Override
    public boolean support(Action action, Object response, ArgumentMetadata argument) {
        return ClassUtils.isAssignable(Action.class, argument.getType());
    }

    @Override
    public Object resolve(Action action, Object response, ArgumentMetadata argument) {
        return action;
    }
    
}
