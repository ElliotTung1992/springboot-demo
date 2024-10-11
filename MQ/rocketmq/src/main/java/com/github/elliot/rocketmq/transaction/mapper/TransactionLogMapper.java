package com.github.elliot.rocketmq.transaction.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.elliot.rocketmq.transaction.domain.TransactionLog;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TransactionLogMapper extends BaseMapper<TransactionLog> {

}
