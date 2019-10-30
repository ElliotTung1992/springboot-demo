package com.github.dge1992.mybatis.domain;

import com.ecer.kafka.connect.oracle.models.BaseLog;

import java.util.Date;


public class BusinessLog extends BaseLog {
    private Integer id;
    private Integer tranceId;
    private Date createTime;
    private Integer isHandle;

    public BusinessLog() {
    }

    public BusinessLog(Integer id, Integer tranceId, Date createTime, Integer isHandle) {
        this.id = id;
        this.tranceId = tranceId;
        this.createTime = createTime;
        this.isHandle = isHandle;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTranceId() {
        return tranceId;
    }

    public void setTranceId(Integer tranceId) {
        this.tranceId = tranceId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getIsHandle() {
        return isHandle;
    }

    public void setIsHandle(Integer isHandle) {
        this.isHandle = isHandle;
    }

    @Override
    public String toString() {
        return "BusinessLog{" +
                "id=" + id +
                ", tranceId=" + tranceId +
                ", createTime=" + createTime +
                ", isHandle=" + isHandle +
                '}';
    }
}
