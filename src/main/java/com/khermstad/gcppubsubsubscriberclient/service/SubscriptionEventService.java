package com.khermstad.gcppubsubsubscriberclient.service;

import com.khermstad.gcppubsubsubscriberclient.entity.SubscriptionEvent;
import com.khermstad.gcppubsubsubscriberclient.repository.SubscriptionEventRepository;
import com.khermstad.gcppubsubsubscriberclient.util.log.IncomingMessageLogUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SubscriptionEventService {

    private SubscriptionEventRepository subscriptionEventRepository;
    private IncomingMessageLogUtil incomingMessageLogUtil;

    public SubscriptionEventService(SubscriptionEventRepository subscriptionEventRepository, IncomingMessageLogUtil incomingMessageLogUtil){
        this.subscriptionEventRepository = subscriptionEventRepository;
        this.incomingMessageLogUtil = incomingMessageLogUtil;
    }

    public SubscriptionEvent saveSubscriptionEvent(SubscriptionEvent subscriptionEvent){
        log.info(incomingMessageLogUtil.subscriptionEvent(subscriptionEvent));
        return subscriptionEventRepository.saveAndFlush(subscriptionEvent);
    }
}
