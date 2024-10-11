package com.github.elliot.rocketmq.transaction.service.impl;

import com.github.elliot.rocketmq.transaction.domain.TransactionLog;
import com.github.elliot.rocketmq.transaction.mapper.TransactionLogMapper;
import com.github.elliot.rocketmq.transaction.service.TransactionLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionLogServiceImpl implements TransactionLogService {

    @Autowired
    private TransactionLogMapper transactionLogMapper;

    @Override
    public TransactionLog get(String transactionId) {
        return transactionLogMapper.selectById(transactionId);
    }
}
