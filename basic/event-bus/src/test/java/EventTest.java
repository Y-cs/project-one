import org.junit.jupiter.api.Test;
import self.project.basic.event.EventBus;
import self.project.basic.event.EventBusBuilder;
import self.project.basic.event.disseminate.LocalDisseminate;
import self.project.basic.event.proxy.CglibTriggerProxyFactory;

/**
 * @author yuanchangshuai
 * @date 2022/6/9-13:51
 * @description
 */
public class EventTest {

    @Test
    public void test1() {
        EventBus eventBus = new EventBusBuilder().setDisseminate(new LocalDisseminate()).setTriggerProxyFactory(new CglibTriggerProxyFactory()).build();
        eventBus.register(new MoveEvent(),MoveEvent.class);
        eventBus.register(new MoveEvent(),MoveEvent.class);
        MoveEvent trigger = eventBus.getTrigger(MoveEvent.class);
        trigger.up(1000);
    }

     public static class MoveEvent {

        public MoveEvent(){}
         public void up(Integer i) {
            System.out.println("up " + i);
        }
         public void down(Integer i) {
            System.out.println("down " + i);
        }
    }

}
