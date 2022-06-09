package self.project.basic.event.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import self.project.basic.event.Event;
import self.project.basic.event.Subscriber;
import self.project.basic.event.disseminate.Disseminate;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author yuanchangshuai
 * @date 2022/6/8-14:42
 * @description
 */
public class CglibTriggerProxyFactory extends AbstractTriggerProxyFactory {

    @Override
    public Object getProxy(Class<?> clazz, Subscriber tSubscriber) {
        Enhancer enhancer = new Enhancer();
        if (clazz.isInterface()) {
            enhancer.setInterfaces(new Class[]{clazz});
        } else {
            enhancer.setSuperclass(clazz);
        }
        enhancer.setCallback(new TriggerProxy(clazz));
        enhancer.setInterceptDuringConstruction(false);
        return enhancer.create();
    }

    private class TriggerProxy implements MethodInterceptor {
        private Class<?> clazz;
        public TriggerProxy(Class<?> clazz) {
            this.clazz = clazz;
        }
        @Override
        public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
            Class<?>[] argsClass = Arrays.stream(objects).map(Object::getClass).toArray(Class<?>[]::new);
            Event event = new Event(clazz, method.getName(), objects, argsClass);
            Disseminate disseminate = CglibTriggerProxyFactory.this.getDisseminate();
            disseminate.poll(event);
            return null;
        }
    }
}
