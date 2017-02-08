package com.marvin.bundle.swing.subscriber;

import com.marvin.bundle.framework.handler.event.GetModelAndViewEvent;
import com.marvin.bundle.framework.handler.event.HandlerEvent;
import com.marvin.bundle.framework.handler.event.HandlerEvents;
import com.marvin.bundle.swing.ApplicationAction;
import com.marvin.component.event.EventSubscriber;

import com.marvin.component.routing.Router;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import javax.swing.Action;

public class RouterSubscriber extends EventSubscriber<HandlerEvent<ApplicationAction, Object>> {

    private final Router router;

    public RouterSubscriber(Router router) {
        this.router = router;
    }
    
    public void onRequest(HandlerEvent<ApplicationAction, Object> event){
        
        if(event instanceof GetModelAndViewEvent) {
            GetModelAndViewEvent<ApplicationAction, Object> e = (GetModelAndViewEvent) event;
            
            ApplicationAction action = e.getRequest();
            
            String command = (String) action.getValue(Action.ACTION_COMMAND_KEY);
            
            Map<String, Object> attributes = this.router.match(command);
        
            if(attributes != null) {
                attributes.forEach(action::putValue);
            }
            
            e.setRequest(action);
        }
    }

    @Override
    public Map<String, Consumer<HandlerEvent<ApplicationAction, Object>>> getSubscribedEvents() {
        Map<String, Consumer<HandlerEvent<ApplicationAction, Object>>> subscribed = new HashMap<>();
        subscribed.put(HandlerEvents.REQUEST, this::onRequest);
        return subscribed;
    }
    
}
