package self.project.basic.event.proxy;

import self.project.basic.event.disseminate.Disseminate;

/**
 * @author yuanchangshuai
 * @date 2022/6/8-14:55
 * @description
 */
public abstract class AbstractTriggerProxyFactory implements TriggerProxyFactory {

    private Disseminate disseminate;

    AbstractTriggerProxyFactory(){}

    public Disseminate getDisseminate() {
        return disseminate;
    }

    public void setDisseminate(Disseminate disseminate) {
        this.disseminate = disseminate;
    }
}
