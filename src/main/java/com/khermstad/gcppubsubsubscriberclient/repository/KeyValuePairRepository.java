package com.khermstad.gcppubsubsubscriberclient.repository;

import com.khermstad.gcppubsubsubscriberclient.entity.KeyValuePair;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KeyValuePairRepository extends JpaRepository<KeyValuePair, Integer> {}
