package com.github.dge1992.quickstart.domain;

import lombok.Data;

/**
 * @Author 小眼睛带鱼
 * @Description
 * @Date 2019/7/2
 **/
@Data
public class ErrorInfo<T> {
    public static final Integer OK = 200;
    public static final Integer ERROR = 500;

    private Integer code;
    private String message;
    private String url;
    private T data;
}
