package com.khermstad.gcppubsubsubscriberclient.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gcp.pubsub.core.PubSubTemplate;
import org.springframework.cloud.gcp.pubsub.integration.AckMode;
import org.springframework.cloud.gcp.pubsub.integration.inbound.PubSubInboundChannelAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.messaging.MessageChannel;

import static com.khermstad.gcppubsubsubscriberclient.constant.ConfigConstant.GCP_PUBSUB_SUBSCRIPTION_NAME;
import static com.khermstad.gcppubsubsubscriberclient.constant.ConfigConstant.SPRING_PUBSUB_INPUT_CHANNEL;

@Configuration
@Slf4j
public class SubscriberConfig {
    
    @Value("${" + GCP_PUBSUB_SUBSCRIPTION_NAME + "}")
    String subscriptionName;
    
    @Bean
    public MessageChannel pubsubInputChannel() {
        return new DirectChannel();
    }

    @Bean
    public PubSubInboundChannelAdapter messageChannelAdapter(
            @Qualifier(SPRING_PUBSUB_INPUT_CHANNEL) MessageChannel inputChannel,
            PubSubTemplate pubSubTemplate) {
        PubSubInboundChannelAdapter adapter =
                new PubSubInboundChannelAdapter(pubSubTemplate, subscriptionName);
        adapter.setOutputChannel(inputChannel);
        adapter.setAckMode(AckMode.AUTO_ACK);
        return adapter;
    }
}
