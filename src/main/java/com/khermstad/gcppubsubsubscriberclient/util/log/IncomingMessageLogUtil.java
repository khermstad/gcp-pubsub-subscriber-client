package com.khermstad.gcppubsubsubscriberclient.util.log;

import com.khermstad.gcppubsubsubscriberclient.entity.SubscriptionEvent;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class IncomingMessageLogUtil {

    public String messageArrived(Message message){
        return "Message Arrived @ [" + LocalDateTime.now() + "] : UUID -> [" + message.getHeaders().getId() + "]";
    }

    public String subscriptionEvent(SubscriptionEvent event) {
        return "Persisting SubscriptionEvent: " + event.toString();
    }

    public String savedSubscriptionEvent(SubscriptionEvent savedEvent){
        return "Saved Subscription Event: " + savedEvent.toString();
    }
}
