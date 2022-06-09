package self.project.basic.event;

import self.project.basic.event.disseminate.AbstractDisseminate;
import self.project.basic.event.proxy.AbstractTriggerProxyFactory;

/**
 * @author yuanchangshuai
 * @date 2022/6/8-15:33
 * @description
 */
public class EventBusBuilder {

    private AbstractDisseminate disseminate;
    private AbstractTriggerProxyFactory triggerProxyFactory;

    public EventBusBuilder() {
    }

    public EventBusBuilder(AbstractDisseminate disseminate, AbstractTriggerProxyFactory triggerProxyFactory) {
        this.disseminate = disseminate;
        this.triggerProxyFactory = triggerProxyFactory;
    }

    public EventBus build() {
        EventBus eventBus = new EventBus(this.triggerProxyFactory);
        this.triggerProxyFactory.setDisseminate(disseminate);
        this.disseminate.setEventBus(eventBus);
        return eventBus;
    }

    public EventBusBuilder setDisseminate(AbstractDisseminate disseminate) {
        this.disseminate = disseminate;
        return this;
    }

    public EventBusBuilder setTriggerProxyFactory(AbstractTriggerProxyFactory triggerProxyFactory) {
        this.triggerProxyFactory = triggerProxyFactory;
        return this;
    }
}
