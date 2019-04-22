package com.khermstad.gcppubsubsubscriberclient.repository;

import com.khermstad.gcppubsubsubscriberclient.entity.SubscriptionEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriptionEventRepository extends JpaRepository<SubscriptionEvent, Integer> {}
