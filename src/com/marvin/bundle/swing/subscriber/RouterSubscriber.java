package com.marvin.bundle.swing.subscriber;

import com.marvin.bundle.swing.action.ApplicationAction;
import event_old.EventConsumer;
import event_old.EventSubscriber;
import com.marvin.bundle.framework.mvc.event.FilterRequestEvent;
import com.marvin.bundle.framework.mvc.event.HandlerEvent;
import com.marvin.bundle.framework.mvc.event.HandlerEvents;

import com.marvin.component.routing.Router;

import java.util.HashMap;
import java.util.Map;
import javax.swing.Action;

public class RouterSubscriber extends EventSubscriber<HandlerEvent> {

    private final Router router;

    public RouterSubscriber(Router router) {
        this.router = router;
    }
    
    public void onRequest(HandlerEvent event){
        if(event instanceof FilterRequestEvent) {
            FilterRequestEvent e = (FilterRequestEvent) event;
            Object request = e.getRequest();
            
            if (request instanceof ApplicationAction) {
                ApplicationAction action = (ApplicationAction) e.getRequest();
                String command = (String) action.getValue(Action.ACTION_COMMAND_KEY);
                Map<String, Object> attributes = this.router.match(command);

                if(attributes != null) {
                    attributes.forEach(action::putValue);
                }
                
               // e.setRequest(action);
            }
        }
    }

    @Override
    public Map<String, EventConsumer<HandlerEvent>> getSubscribedEvents() {
        Map<String, EventConsumer<HandlerEvent>> subscribed = new HashMap<>();
        subscribed.put(HandlerEvents.FILTER_REQUEST, this::onRequest);
        return subscribed;
    }
    
}
