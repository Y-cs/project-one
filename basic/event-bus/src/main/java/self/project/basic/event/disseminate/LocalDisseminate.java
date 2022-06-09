package self.project.basic.event.disseminate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.*;
import self.project.basic.event.Event;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author yuanchangshuai
 * @date 2022/6/8-16:23
 * @description
 */
public class LocalDisseminate extends AbstractDisseminate {

    @Override
    public void poll(Event event) {
        executeEvent(event);
    }


}
