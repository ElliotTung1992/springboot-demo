package com.github.elliot.rocketmq.transaction.service;

import com.github.elliot.rocketmq.transaction.domain.TransactionLog;

public interface TransactionLogService {

    TransactionLog get(String transactionId);
}
