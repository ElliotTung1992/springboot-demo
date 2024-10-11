package com.github.elliot.rocketmq.transaction.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("transaction_log")
@Data
public class TransactionLog {

    private String id;

    private String business;

    private String foreignKey;
}
