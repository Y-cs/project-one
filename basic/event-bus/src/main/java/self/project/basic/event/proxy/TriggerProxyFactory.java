package self.project.basic.event.proxy;

import self.project.basic.event.Subscriber;

/**
 * @author yuanchangshuai
 * @date 2022/6/8-13:27
 * @description
 */
public interface TriggerProxyFactory {

    /**
     * 获取代理对象
     * @param clazz
     * @param tSubscriber
     * @return
     */
    Object getProxy(Class<?> clazz, Subscriber tSubscriber);
}
