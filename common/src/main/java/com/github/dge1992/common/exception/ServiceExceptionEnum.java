package com.github.dge1992.common.exception;

/**
 * 抽象接口
 *
 * @author dongganen
 */
public interface ServiceExceptionEnum {

    /**
     * 获取异常编码
     */
    Integer getCode();

    /**
     * 获取异常信息
     */
    String getMessage();
}
