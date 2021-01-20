package com.github.dge1992.commonforward.biz.post.strategy;

import com.github.dge1992.commonforward.api.model.CommonReceiveObject;

/**
 * @author 董感恩
 * @date 2020-05-09 17:51
 * 策略接口
 */
public interface PostStrategy {

    /**
     * 后置处理
     *
     * @param commonReceiveObject 接收对象
     * @param resultStr           字符串结果集
     * @author dge
     * @date 2021-01-14 10:29
     */
    void postExecute(CommonReceiveObject commonReceiveObject, String resultStr);
}
