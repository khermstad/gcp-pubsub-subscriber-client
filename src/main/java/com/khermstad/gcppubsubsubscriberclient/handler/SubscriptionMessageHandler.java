package com.khermstad.gcppubsubsubscriberclient.handler;

import com.khermstad.gcppubsubsubscriberclient.entity.SubscriptionEvent;
import com.khermstad.gcppubsubsubscriberclient.service.SubscriptionEventService;
import com.khermstad.gcppubsubsubscriberclient.util.log.IncomingMessageLogUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.stereotype.Component;

import static com.khermstad.gcppubsubsubscriberclient.constant.ConfigConstant.SPRING_PUBSUB_INPUT_CHANNEL;

@Component
@Slf4j
public class SubscriptionMessageHandler {

    private SubscriptionEventService subscriptionEventService;
    private IncomingMessageLogUtil incomingMessageLogUtil;

    public SubscriptionMessageHandler(SubscriptionEventService subscriptionEventService, IncomingMessageLogUtil incomingMessageLogUtil){
        this.subscriptionEventService = subscriptionEventService;
        this.incomingMessageLogUtil = incomingMessageLogUtil;
    }

    private void handleIncomingMessage(Message message){
        log.info(incomingMessageLogUtil.messageArrived(message));
        SubscriptionEvent savedEvent = subscriptionEventService.saveSubscriptionEvent(new SubscriptionEvent(message));
        log.info(incomingMessageLogUtil.savedSubscriptionEvent(savedEvent));
    }
    
    @Bean
    @ServiceActivator(inputChannel = SPRING_PUBSUB_INPUT_CHANNEL)
    public MessageHandler messageReceiver() {
        return this::handleIncomingMessage;
    }
    
}
