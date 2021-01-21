package com.github.dge1992.commonforwardcommon.enums;

/**
 * @author 董感恩
 * @date 2020-07-07 11:37
 * HttpMethod枚举
 */
public enum HttpMethodEnum {

    /**
     * Get请求
     */
    GET(0, "Get"),
    /**
     * Post请求,参数是Json形式
     */
    POST_JSON(1, "Post-Json"),
    /**
     * Post请求,参数是Param形式
     */
    POST_PARAM(2, "Post-param");

    private Integer methodCode;
    private String desc;

    HttpMethodEnum(Integer methodCode, String desc) {
        this.methodCode = methodCode;
        this.desc = desc;
    }

    public Integer getMethodCode() {
        return methodCode;
    }

    public String getDesc() {
        return desc;
    }

    public static HttpMethodEnum getByMethodCode(Integer methodCode) {
        HttpMethodEnum[] values = HttpMethodEnum.values();
        for (HttpMethodEnum methodEnum : values) {
            if (methodEnum.getMethodCode().equals(methodCode)) {
                return methodEnum;
            }
        }
        return null;
    }
}
