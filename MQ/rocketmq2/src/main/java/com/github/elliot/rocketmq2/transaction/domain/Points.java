package com.github.elliot.rocketmq2.transaction.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("points")
@Data
public class Points {

    private Long id;
    private Long userId;
    private String orderNo;
    private Integer points;
    private String remarks;

}
