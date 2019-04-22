package com.khermstad.gcppubsubsubscriberclient.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.messaging.Message;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

import static com.khermstad.gcppubsubsubscriberclient.constant.ConfigConstant.SUBSCRIPTION_EVENT_TABLE;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = SUBSCRIPTION_EVENT_TABLE)
public class SubscriptionEvent {

    @Id
    String uuid;
    String payload;
    LocalDateTime received_timestamp;

    public SubscriptionEvent(Message message) {
        this.uuid = message.getHeaders().getId().toString();
        this.payload = message.getPayload().toString();
        this.received_timestamp = LocalDateTime.now();
    }
}
