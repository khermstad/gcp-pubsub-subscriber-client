package com.khermstad.gcppubsubsubscriberclient.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import static com.khermstad.gcppubsubsubscriberclient.constant.ConfigConstant.KEY_VALUE_PAIR_TABLE;

@Entity
@Data
@Table(name = KEY_VALUE_PAIR_TABLE)
public class KeyValuePair {
    @Id
    String key;
    String value;
}
