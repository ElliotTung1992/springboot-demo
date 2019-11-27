package com.ecer.kafka.connect.oracle.models;

import lombok.Data;

/**
 * 解析数据实体
 */
@Data
public class AnalysisSourceParam {
    private String xid;
    private Long scn;
}
