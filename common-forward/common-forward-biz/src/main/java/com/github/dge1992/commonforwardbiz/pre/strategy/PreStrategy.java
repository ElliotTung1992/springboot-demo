package com.github.dge1992.commonforwardbiz.pre.strategy;


import com.github.dge1992.commonforwardapi.model.CommonReceiveObject;

/**
 * @author dge
 * @version 1.0
 * @date 2021-01-12 17:00
 * 前置策略接口
 */
public interface PreStrategy {

    /**
     * 前置处理
     *
     * @param receiveObject 接收的实体对象
     * @author dge
     * @date 2021-01-13 16:50
     */
    void preExecute(CommonReceiveObject receiveObject);
}
