package com.github.elliot.flinkdemo.bean;

import java.io.Serializable;

public class WaterSensor implements Serializable {

    private String id;

    private Long st;

    private Integer vc;

    public WaterSensor() {
    }

    public WaterSensor(String id, Long st, Integer vc) {
        this.id = id;
        this.st = st;
        this.vc = vc;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getSt() {
        return st;
    }

    public void setSt(Long st) {
        this.st = st;
    }

    public Integer getVc() {
        return vc;
    }

    public void setVc(Integer vc) {
        this.vc = vc;
    }
}
