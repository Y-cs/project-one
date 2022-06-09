package self.project.basic.event.disseminate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.*;
import self.project.basic.event.Event;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author yuanchangshuai
 * @date 2022/6/8-16:23
 * @description
 */
public class RabbitDisseminate extends AbstractDisseminate {
    private static final String EXCHANGE = "listener.exchange";
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private final Connection connection;
    private final Channel producerChannel;
    private final Channel consumerChannel;

    public RabbitDisseminate(Connection connection) {
        this.connection = connection;
        this.producerChannel = getChannel();
        //广播模式的交换器
        try {
            this.producerChannel.exchangeDeclare(EXCHANGE, BuiltinExchangeType.FANOUT, false, true, null);
        } catch (IOException e) {
            throw new Error("create listener exchange error");
        }
        this.consumerChannel = getChannel();
        String queue;
        try {
            //临时队列
            queue = this.consumerChannel.queueDeclare().getQueue();
            //绑定
            this.consumerChannel.queueBind(queue, EXCHANGE, "");
        } catch (IOException e) {
            throw new Error("create temporary queue error");
        }
        try {
            this.consumerChannel.basicConsume(queue, true, new RabbitConsumer(this.consumerChannel));
        } catch (IOException e) {
            throw new RuntimeException("consume listener exception");
        }
    }

    private Channel getChannel() {
        try {
            return this.connection.createChannel();
        } catch (IOException e) {
            throw new Error("create channel error");
        }
    }

    @Override
    public void poll(Event event) {
        try {
            byte[] bytes = OBJECT_MAPPER.writer().writeValueAsBytes(event);
            this.producerChannel.basicPublish(EXCHANGE, "", null, bytes);
        } catch (IOException e) {
            throw new RuntimeException("poll event error");

        }
    }

    class RabbitConsumer extends DefaultConsumer {

        public RabbitConsumer(Channel channel) {
            super(channel);
        }

        @Override
        public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
            Event event = OBJECT_MAPPER.reader().readValue(body, Event.class);
            RabbitDisseminate.this.executeEvent(event);
        }
    }

    public void close() throws IOException, TimeoutException {
        this.producerChannel.close();
        this.consumerChannel.close();
    }



}
