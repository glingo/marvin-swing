package com.marvin.bundle.swing.controller;

import com.marvin.component.kernel.controller.ArgumentResolver;
import com.marvin.component.kernel.controller.ArgumentValueResolverInterface;
import com.marvin.component.kernel.controller.argumentResolver.DefaultValueResolver;
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
