package com.marvin.bundle.swing.controller;

import com.marvin.bundle.framework.controller.argument.ArgumentResolver;
import com.marvin.bundle.framework.controller.argument.ArgumentValueResolverInterface;
import com.marvin.bundle.framework.controller.argument.argumentResolver.DefaultValueResolver;
import java.util.ArrayList;
import java.util.List;
import com.marvin.bundle.swing.controller.argumentResolver.ActionAtributeValueResolver;
import com.marvin.bundle.swing.controller.argumentResolver.ActionValueResolver;

public class ActionArgumentResolver extends ArgumentResolver {

    public ActionArgumentResolver() {
        super(getDefaultResolvers());
    }
    
    public ActionArgumentResolver(List<ArgumentValueResolverInterface> resolvers) {
        super(resolvers);
    }
    
    public static List<ArgumentValueResolverInterface> getDefaultResolvers(){
        List<ArgumentValueResolverInterface> resolvers = new ArrayList<>();
        
        resolvers.add(new DefaultValueResolver());
        resolvers.add(new ActionAtributeValueResolver());
        resolvers.add(new ActionValueResolver());
        
        return resolvers;
    } 
    
    
}
