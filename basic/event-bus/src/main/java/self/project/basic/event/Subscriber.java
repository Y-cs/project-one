package self.project.basic.event;

import self.project.basic.event.proxy.TriggerProxyFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author yuanchangshuai
 * @date 2022/6/8-13:24
 * @description
 */
public class Subscriber {

    private Class<?> type;

    private Object trigger;

    private Set<Object> recipients;

    protected Subscriber(Class<?> t, TriggerProxyFactory triggerProxyFactory) {
        type = t;
        trigger = triggerProxyFactory.getProxy(t, this);
        recipients = new CopyOnWriteArraySet<>();
    }

    public void addRecipient(Object t) {
        if (trigger == t) {
            throw new RuntimeException("not register trigger");
        }
        if (!type.isAssignableFrom(t.getClass())) {
            throw new RuntimeException("not register trigger");
        }
        recipients.add(t);
    }

    public void removeRecipient(Object t) {
        recipients.remove(t);
    }

    public void poll(Event event) {
        try {
            Method method = type.getMethod(event.getMethodName(), event.getArgsClass());
            for (Object recipient : recipients) {
                method.invoke(recipient, event.getArgs());
            }
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("recipient method not found", e);
        } catch (InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException("recipient method invoke error", e);
        }
    }

    public Class<?> getType() {
        return type;
    }

    public Object getTrigger() {
        return trigger;
    }

    public Set<Object> getRecipients() {
        return recipients;
    }
}
