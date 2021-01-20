package com.github.dge1992.commonforward.api.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;

/**
 * @author dge
 * @version 1.0
 * @date 2021-01-04 17:27
 * 转发服务接收公共对象
 */
public class BaseReceiveObject implements Serializable {

    /**
     * 唯一编号
     */
    private String uuid;
    /**
     * 发起方
     */
    private String from;
    /**
     * 接收方
     */
    private String to;
    /**
     * URL
     */
    private String URL;
    /**
     * URLCode
     */
    private String URLCode;
    /**
     * 请求方法
     */
    private Integer method;
    /**
     * 请求头列表
     */
    private HashMap<String, String> headMap;
    /**
     * 请求参数
     */
    private HashMap<String, Object> paramsMap;
    /**
     * 备注
     */
    private String remarks;
    /**
     * 发起时间
     */
    private Date createTime;
    /**
     * 结果
     */
    private String result;
    /**
     * 是否成功
     */
    private Boolean isSuccess;

    public String getUuid() {
        return this.uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public String getURLCode() {
        return URLCode;
    }

    public void setURLCode(String URLCode) {
        this.URLCode = URLCode;
    }

    public Integer getMethod() {
        return method;
    }

    public void setMethod(Integer method) {
        this.method = method;
    }

    public HashMap<String, String> getHeadMap() {
        return headMap;
    }

    public void setHeadMap(HashMap<String, String> headMap) {
        this.headMap = headMap;
    }

    public HashMap<String, Object> getParamsMap() {
        return paramsMap;
    }

    public void setParamsMap(HashMap<String, Object> paramsMap) {
        this.paramsMap = paramsMap;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Boolean getIsSuccess() {
        return this.isSuccess;
    }

    public void setIsSuccess(Boolean success) {
        this.isSuccess = success;
    }

    @Override
    public String toString() {
        return "BaseReceiveObject{" +
                "uuid='" + uuid + '\'' +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", URL='" + URL + '\'' +
                ", URLCode='" + URLCode + '\'' +
                ", method=" + method +
                ", headMap=" + headMap +
                ", paramsMap=" + paramsMap +
                ", remarks='" + remarks + '\'' +
                ", createTime=" + createTime +
                ", result='" + result + '\'' +
                ", isSuccess=" + isSuccess +
                '}';
    }
}
