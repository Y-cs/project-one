package self.project.basic.event;

import self.project.basic.event.proxy.TriggerProxyFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author yuanchangshuai
 */
public class EventBus {

    /**
     * 扳机工厂
     */
    private TriggerProxyFactory triggerProxyFactory;

    /**
     * 监听缓存
     */
    private final Map<Class<?>, Subscriber> subscriberCache = new ConcurrentHashMap<>();

    EventBus(TriggerProxyFactory triggerProxyFactory) {
        this.triggerProxyFactory = triggerProxyFactory;
    }

    public <T> void register(T t, Class<?> clazz) {
        if (!clazz.isAssignableFrom(t.getClass())) {
            throw new RuntimeException("register object must implements register class");
        }
        Subscriber tSubscriber = createIfAbsent(clazz);
        tSubscriber.addRecipient(t);
        subscriberCache.put(tSubscriber.getType(), tSubscriber);
    }

    public <T> void unRegister(T t) {
        Subscriber subscriber = subscriberCache.get(t.getClass());
        if (subscriber != null) {
            subscriber.removeRecipient(t);
        }
    }

    public <T> T getTrigger(Class<T> clazz) {
        Subscriber tSubscriber = createIfAbsent(clazz);
        return (T) tSubscriber.getTrigger();
    }

    private Subscriber createIfAbsent(Class<?> t) {
        return subscriberCache.computeIfAbsent(t, (k) -> new Subscriber(t, triggerProxyFactory));
    }

    public void poll(Event event) {
        Subscriber subscriber = subscriberCache.get(event.getClazz());
        subscriber.poll(event);
    }

}
