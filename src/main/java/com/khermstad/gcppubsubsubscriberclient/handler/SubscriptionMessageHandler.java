package com.khermstad.gcppubsubsubscriberclient.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.MessageHandler;
import org.springframework.stereotype.Component;

import static com.khermstad.gcppubsubsubscriberclient.constant.ConfigConstant.SPRING_PUBSUB_INPUT_CHANNEL;

@Component
@Slf4j
public class SubscriptionMessageHandler {
    
    @Bean
    @ServiceActivator(inputChannel = SPRING_PUBSUB_INPUT_CHANNEL)
    public MessageHandler messageReceiver() {
        return message -> {
            log.info("Message arrived! Payload: " + new String((byte[]) message.getPayload()));
        };
    }
    
}
