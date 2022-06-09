package self.project.basic.event.disseminate;

import self.project.basic.event.Event;

/**
 * @author yuanchangshuai
 * @date 2022/6/8-13:18
 * @description
 */
public interface Disseminate {

    /**
     * 传播
     * @param event
     */
    void poll(Event event);

}
