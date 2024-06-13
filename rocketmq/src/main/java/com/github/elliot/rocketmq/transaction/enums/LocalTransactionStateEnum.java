package com.github.elliot.rocketmq.transaction.enums;

public enum LocalTransactionStateEnum {

    //提交事务消息，消费者可以看到此消息
    COMMIT_MESSAGE,
    //回滚事务消息，消费者不会看到此消息
    ROLLBACK_MESSAGE,
    //事务未知状态，需要调用事务状态回查，确定此消息是提交还是回滚
    UNKNOW;
}
