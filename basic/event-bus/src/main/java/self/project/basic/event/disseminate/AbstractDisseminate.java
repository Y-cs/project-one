package self.project.basic.event.disseminate;

import self.project.basic.event.Event;
import self.project.basic.event.EventBus;
import self.project.basic.event.Subscriber;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author yuanchangshuai
 * @date 2022/6/8-15:17
 * @description
 */
public abstract class AbstractDisseminate implements Disseminate {

    private EventBus eventBus;

    AbstractDisseminate(){}

    public void executeEvent(Event event) {
        eventBus.poll(event);
    }

    public void setEventBus(EventBus eventBus) {
        this.eventBus = eventBus;
    }



}
