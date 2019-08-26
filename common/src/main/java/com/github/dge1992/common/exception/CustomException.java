package com.github.dge1992.common.exception;

import lombok.Data;

/**
 * 封装自定义的异常
 *
 * @author dge1992
 */
@Data
public class CustomException extends RuntimeException {

    private Integer code;

    private String message;

    public CustomException(ServiceExceptionEnum serviceExceptionEnum) {
        this.code = serviceExceptionEnum.getCode();
        this.message = serviceExceptionEnum.getMessage();
    }

}
