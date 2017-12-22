package com.marvin.bundle.swing.subscriber;

import com.marvin.bundle.framework.mvc.controller.event.FilterRequestEvent;
import com.marvin.bundle.swing.action.ApplicationAction;
import com.marvin.component.event.dispatcher.DispatcherInterface;
import com.marvin.component.event.handler.Handler;
import com.marvin.component.event.subscriber.Subscriber;

import com.marvin.component.routing.Router;

import java.util.Map;
import javax.swing.Action;

public class RouterSubscriber extends Subscriber {

    private final Router router;

    public RouterSubscriber(Router router) {
        this.router = router;
    }
    
    
    public Handler<FilterRequestEvent> onRequest(){
        return event -> {
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
        };
    }

    @Override
    public void subscribe(DispatcherInterface dispatcher) {
        dispatcher.register(FilterRequestEvent.class, onRequest());
    }
}
