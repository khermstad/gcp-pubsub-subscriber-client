package com.khermstad.gcppubsubsubscriberclient.handler;

import com.khermstad.gcppubsubsubscriberclient.entity.KeyValuePair;
import com.khermstad.gcppubsubsubscriberclient.repository.KeyValuePairRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.MessageHandler;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

import static com.khermstad.gcppubsubsubscriberclient.constant.ConfigConstant.SPRING_PUBSUB_INPUT_CHANNEL;

@Component
@Slf4j
public class SubscriptionMessageHandler {
    
    private final KeyValuePairRepository keyValuePairRepository;
    
    public SubscriptionMessageHandler(KeyValuePairRepository keyValuePairRepository){
        this.keyValuePairRepository = keyValuePairRepository;
    }
    
    @Bean
    @ServiceActivator(inputChannel = SPRING_PUBSUB_INPUT_CHANNEL)
    public MessageHandler messageReceiver() {
        return message -> {
            log.info("Message Arrived @ [" + LocalDateTime.now() + "] Payload: " + new String((byte[]) message.getPayload()));
            KeyValuePair kvp = new KeyValuePair();
            kvp.setKey(message.getHeaders().get("key").toString());
            kvp.setValue(message.getHeaders().get("value").toString());
            log.info(kvp.toString());
            log.info("Saving KeyValuePair to Database");
            keyValuePairRepository.save(kvp);
        };
    }
    
}
