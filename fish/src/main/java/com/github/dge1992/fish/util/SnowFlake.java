package com.github.dge1992.fish.util;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * 雪花算法
 * @author dge
 * @date 2021-07-05 17:07
 */
public class SnowFlake {

    /**
     * 起始时间
     */
    private final long START_TIME_MILLIS = LocalDateTime
            .of(2021, 1, 1, 0, 0, 0)
            .toInstant(ZoneOffset.of("+8")).toEpochMilli();

    /**
     * 序号最大位数
     */
    private final long SERIAL_NUMBER_BITS = 12;
    /**
     * 数据中心标识最大位数
     */
    private final long DATA_CENTER_BITS = 5;
    /**
     * 机器码最大位数
     */
    private final long WORKER_BITS = 5;

    private final long MAX_SERIAL_NUMBER = -1 * (-1 * 2 << SERIAL_NUMBER_BITS);

    private final long MAX_DATA_CENTER_ID = -1 * (-1 * 2 << DATA_CENTER_BITS);

    private final long MAX_WORKER_ID = -1 * (-1 * 2 << WORKER_BITS);

    private final long MAX_WORKER_LEFT_SHIFT = SERIAL_NUMBER_BITS;

    private final long DATA_CENTER_LEFT_SHIFT = MAX_WORKER_LEFT_SHIFT + WORKER_BITS;

    private final long TIME_MILLIS_LEFT_SHIFT = DATA_CENTER_LEFT_SHIFT + DATA_CENTER_BITS;


    private long dataCenterId;

    private long workerId;

    private long serialNumberId = 0L;

    private long lastTimeMillis = -1L;

    public SnowFlake(long dataCenterId, long workerId){
        if(dataCenterId > MAX_DATA_CENTER_ID){
            throw new IllegalArgumentException("dataCenterId error");
        }
        if(workerId > MAX_WORKER_ID){
            throw new IllegalArgumentException("workerId error");
        }
        this.dataCenterId = dataCenterId;
        this.workerId = workerId;
    }


    private synchronized long nextId(){
        long currentTimeMillis = getCurrentTimeMillis();
        if(currentTimeMillis < lastTimeMillis){
            throw new IllegalArgumentException("currentTimeMillis error");
        }
        if(currentTimeMillis == lastTimeMillis){
            serialNumberId = (serialNumberId + 1) & MAX_SERIAL_NUMBER;
            if(serialNumberId == 0){
                currentTimeMillis = getNextMillis();
            }
        }else{
            serialNumberId = 0;
        }

        lastTimeMillis = currentTimeMillis;

        return (currentTimeMillis - START_TIME_MILLIS) << TIME_MILLIS_LEFT_SHIFT |
                dataCenterId << DATA_CENTER_LEFT_SHIFT |
                workerId <<  MAX_WORKER_LEFT_SHIFT |
                serialNumberId;
    }

    private long getNextMillis() {
        long currentTimeMillis = getCurrentTimeMillis();
        while (currentTimeMillis <= lastTimeMillis){
            currentTimeMillis = getCurrentTimeMillis();
        }
        return currentTimeMillis;
    }

    private Long getCurrentTimeMillis(){
        return System.currentTimeMillis();
    }

    public static void main(String[] args) {
        SnowFlake mySnowFlake = new SnowFlake(1, 2);
        System.out.println(mySnowFlake.nextId());
    }

}
