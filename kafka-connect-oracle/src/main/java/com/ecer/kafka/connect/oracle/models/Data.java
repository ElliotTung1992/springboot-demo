package com.ecer.kafka.connect.oracle.models;

import java.util.Date;

/**
 *  
 * @author Erdem Cer (erdemcer@gmail.com)
 */

public class Data{

    private String xid;
    private long scn;
    private String segOwner;
    private String segName;
    private String sqlRedo;
    private Date date;
    private String operation;

    public Data(Long scn,String segOwner,String segName,String sqlRedo,Date date,String operation){
        super();
        this.scn=scn;
        this.segOwner=segOwner;
        this.segName=segName;
        this.sqlRedo=sqlRedo;
        this.date=date;
        this.operation=operation;
    }

    public Data(String xid, Long scn,String segOwner,String segName,String sqlRedo,Date date,String operation){
        super();
        this.xid=xid;
        this.scn=scn;
        this.segOwner=segOwner;
        this.segName=segName;
        this.sqlRedo=sqlRedo;
        this.date=date;
        this.operation=operation;
    }

    public String getXid() {
        return xid;
    }

    public void setXid(String xid) {
        this.xid = xid;
    }

    public long getScn(){
        return scn;
    }

    public String getSegOwner(){
        return segOwner;
    }

    public String getSegName(){
        return segName;
    }

    public String getSqlRedo(){
        return sqlRedo;
    }

    public String getOperation(){
        return operation;
    }

    public void setScn(Long scn){
        this.scn=scn;
    }

    public void setSegOwner(String segOwner){
        this.segOwner=segOwner;
    }

    public void setSegName(String segName){
        this.segName=segName;
    }

    public void setSqlRedo(String sqlRedo){
        this.sqlRedo=sqlRedo;
    }

    public void setOperation(String operation){
        this.operation=operation;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Data{" +
                "xid='" + xid + '\'' +
                ", scn=" + scn +
                ", segOwner='" + segOwner + '\'' +
                ", segName='" + segName + '\'' +
                ", sqlRedo='" + sqlRedo + '\'' +
                ", date=" + date +
                ", operation='" + operation + '\'' +
                '}';
    }
}