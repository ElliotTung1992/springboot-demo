package com.github.elliot.rocketmq.transaction.controller;


import com.github.elliot.rocketmq.transaction.domain.TransactionLog;
import com.github.elliot.rocketmq.transaction.mapper.TransactionLogMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
public class TestController {

    @Autowired
    private TransactionLogMapper transactionLogMapper;

    @GetMapping("/test")
    public void test(){
        TransactionLog transactionLog = new TransactionLog();
        transactionLog.setId("1");
        transactionLog.setBusiness("2");
        transactionLog.setForeignKey("3");
        transactionLogMapper.insert(transactionLog);
    }
}
