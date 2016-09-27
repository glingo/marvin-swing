package com.marvin.bundle.swing;

import java.util.Stack;

import com.marvin.component.event.EventDispatcher;
import com.marvin.component.kernel.controller.ArgumentResolver;
import com.marvin.component.kernel.controller.ControllerReference;
import com.marvin.component.kernel.controller.ControllerResolverInterface;
import com.marvin.component.util.ReflectionUtils;
import java.awt.Event;
import java.util.Arrays;
import java.util.List;
import javax.swing.AbstractAction;

public class ActionHandler {
    
    private final Stack<Event> requestStack;
    private final ControllerResolverInterface ctrlResolver;
    private final EventDispatcher dispatcher;
    private final ArgumentResolver argsResolver;
    
    public ActionHandler(EventDispatcher dispatcher, ControllerResolverInterface ctrlResolver, ArgumentResolver argsResolver) {
        this.dispatcher = dispatcher;
        this.ctrlResolver = ctrlResolver;
        this.requestStack = new Stack<>();
        this.argsResolver = argsResolver;
    }
    
    public ActionHandler(EventDispatcher dispatcher, ControllerResolverInterface ctrlResolver, Stack<Event> requestStack, ArgumentResolver argsResolver) {
        this.dispatcher = dispatcher;
        this.ctrlResolver = ctrlResolver;
        this.requestStack = requestStack;
        this.argsResolver = argsResolver;
    }
    
    public void handle(AbstractAction action) throws Exception {
        System.out.println(Arrays.toString(action.getKeys()));
        
        ControllerReference controller = this.ctrlResolver.resolveController(action);
        
        List<Object> arguments = this.argsResolver.getArguments(action, controller);
        
        Object controllerResponse = ReflectionUtils.invokeMethod(controller.getAction(), 
                controller.getHolder(), arguments.toArray());
        
        System.out.println("reponse : " + controllerResponse);
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        
        sb
            .append("\n------")
            .append(super.toString())
            .append("------")
            .append("\n\t")
            .append(this.ctrlResolver)
            .append("\n\t")
            .append(this.dispatcher)
            .append("\n\t")
            .append(this.requestStack)
            .append("\n");
        
        return sb.toString();
    }

}
