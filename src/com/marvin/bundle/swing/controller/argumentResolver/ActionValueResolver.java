package com.marvin.bundle.swing.controller.argumentResolver;

import com.marvin.component.kernel.controller.ArgumentMetadata;
import com.marvin.component.kernel.controller.ArgumentValueResolverInterface;
import com.marvin.component.util.ClassUtils;
import javax.swing.Action;

public class ActionValueResolver implements ArgumentValueResolverInterface<Action>  {

    @Override
    public boolean support(Action action, ArgumentMetadata argument) {
        return ClassUtils.isAssignable(Action.class, argument.getType());
    }

    @Override
    public Object resolve(Action action, ArgumentMetadata argument) {
        return action;
    }
    
}
