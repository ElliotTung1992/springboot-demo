package com.github.dge1992.commonforward.api.model;

/**
 * @author 董感恩
 * @date 2020-02-24 17:26
 * @desc Http返回结果对象
 */
public class HttpForwardResult extends BaseResult {

    /**
     * code
     */
    private Integer code;
    /**
     * message
     */
    private String message;

    public HttpForwardResult(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "HttpForwardResult{" +
                "code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}
