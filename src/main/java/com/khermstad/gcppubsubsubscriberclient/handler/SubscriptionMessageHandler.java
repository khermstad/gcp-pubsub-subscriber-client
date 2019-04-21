package com.khermstad.gcppubsubsubscriberclient.handler;

import com.khermstad.gcppubsubsubscriberclient.entity.SubscriptionEvent;
import com.khermstad.gcppubsubsubscriberclient.repository.SubscriptionEventRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

import static com.khermstad.gcppubsubsubscriberclient.constant.ConfigConstant.SPRING_PUBSUB_INPUT_CHANNEL;

@Component
@Slf4j
public class SubscriptionMessageHandler {

    private SubscriptionEventRepository subscriptionEventRepository;

    public SubscriptionMessageHandler(SubscriptionEventRepository subscriptionEventRepository){
        this.subscriptionEventRepository = subscriptionEventRepository;
    }

    public void handleIncomingMessage(Message message){
        UUID messageID = message.getHeaders().getId();
        log.info("Message Arrived @ [" + LocalDateTime.now() + "] : UUID -> [" + messageID + "]");
        SubscriptionEvent event = new SubscriptionEvent(messageID.toString(), message.getPayload().toString(), LocalDateTime.now());
        log.info("SubscriptionEvent to Persist: " + event.toString());
        SubscriptionEvent savedEvent = subscriptionEventRepository.save(event);
        log.info("Saved Subscription Event: " + savedEvent.toString());
    }
    
    @Bean
    @ServiceActivator(inputChannel = SPRING_PUBSUB_INPUT_CHANNEL)
    public MessageHandler messageReceiver() {
        return message -> {
            handleIncomingMessage(message);
        };
    }
    
}
