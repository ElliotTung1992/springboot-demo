package com.github.dge1992.commonforwardapi.model;

import java.util.Arrays;

/**
 * @author dge
 * @version 1.0
 * @date 2021-01-04 16:45
 * 回调转发服务接收对象
 */
public class CommonReceiveObject extends PrePostReceiveObject {

    /**
     * 回调类名
     */
    private String callbackClassName;
    /**
     * 回调方法名
     */
    private String callbackMethodName;
    /**
     * 回调方法参数
     */
    private String[] callbackParamArr;

    public String getCallbackClassName() {
        return callbackClassName;
    }

    public void setCallbackClassName(String callbackClassName) {
        this.callbackClassName = callbackClassName;
    }

    public String getCallbackMethodName() {
        return callbackMethodName;
    }

    public void setCallbackMethodName(String callbackMethodName) {
        this.callbackMethodName = callbackMethodName;
    }

    public String[] getCallbackParamArr() {
        return callbackParamArr;
    }

    public void setCallbackParamArr(String[] callbackParamArr) {
        this.callbackParamArr = callbackParamArr;
    }

    @Override
    public String toString() {
        return "ReceiveObject{" +
                ", callbackClassName='" + callbackClassName + '\'' +
                ", callbackMethodName='" + callbackMethodName + '\'' +
                ", callbackParamArr=" + Arrays.toString(callbackParamArr) +
                '}';
    }
}
