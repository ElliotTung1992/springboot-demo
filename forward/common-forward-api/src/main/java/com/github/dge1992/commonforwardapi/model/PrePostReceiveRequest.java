package com.github.dge1992.commonforwardapi.model;

/**
 * @author dge
 * @version 1.0
 * @date 2021-01-12 16:18
 * 前置后置接收对象
 */
public class PrePostReceiveRequest extends BaseReceiveRequest {

    /**
     * 前置策略编号
     */
    private String preStrategyCode;
    /**
     * 后置策略编号
     */
    private String postStrategyCode;

    public String getPreStrategyCode() {
        return preStrategyCode;
    }

    public void setPreStrategyCode(String preStrategyCode) {
        this.preStrategyCode = preStrategyCode;
    }

    public String getPostStrategyCode() {
        return postStrategyCode;
    }

    public void setPostStrategyCode(String postStrategyCode) {
        this.postStrategyCode = postStrategyCode;
    }

    @Override
    public String toString() {
        return "PrePostReceiveObject{" +
                "preStrategyCode='" + preStrategyCode + '\'' +
                ", postStrategyCode='" + postStrategyCode + '\'' +
                '}';
    }
}
