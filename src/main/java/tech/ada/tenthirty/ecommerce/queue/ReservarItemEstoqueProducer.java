package tech.ada.tenthirty.ecommerce.queue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.AMQP;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import tech.ada.tenthirty.ecommerce.queue.payload.ReservarEstoqueRequest;

@Service
@RequiredArgsConstructor
public class ReservarItemEstoqueProducer {
    private final RabbitTemplate rabbitTemplate;

    private final Queue queue;

    private final ObjectMapper objectMapper;

    public void enviar(ReservarEstoqueRequest reservarEstoque) throws JsonProcessingException {
        String message = objectMapper.writeValueAsString(reservarEstoque);
        rabbitTemplate.convertSendAndReceive(queue.getName(), message);
    }

}
