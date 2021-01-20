package com.github.dge1992.commonforward.biz.pre.strategy;

import com.github.dge1992.commonforward.api.model.CommonReceiveObject;

/**
 * @author dge
 * @version 1.0
 * @date 2021-01-12 17:00
 * 前置策略接口
 */
public interface PreStrategy {

    /**
     * 前置处理
     * @param receiveObject 接收的实体对象
     * @return com.github.dge1992.commonforward.api.model.CallbackReceiveObject
     * @author dge
     * @date 2021-01-13 16:50
     */
    void preExecute(CommonReceiveObject receiveObject);
}
