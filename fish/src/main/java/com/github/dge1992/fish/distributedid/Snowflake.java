package com.github.dge1992.fish.distributedid;

public class Snowflake {

    private static final int SEQUENCE_BIT = 12;
    private static final int WORK_ID_BIT = 10;

    private static final int WORK_ID_LEFT = SEQUENCE_BIT;
    private static final int TIME_STAMP_LEFT = WORK_ID_LEFT + WORK_ID_BIT;

    private static final int MAX_SEQUENCE = -1 ^ (-1 << SEQUENCE_BIT);
    private static final int MAX_WORK_ID = -1 ^ (-1 << WORK_ID_BIT);

    private static final long initTimeStamp = 1704038400000L;

    private static long lastTimeStamp = -1;

    private static int sequence = 0;
    private static final long workId = 626;

    public synchronized static long getSnowflake(){
        long newTimeStamp = getNewTimeStamp();
        if(lastTimeStamp > newTimeStamp){
            throw new RuntimeException("lastTimeStamp:" + lastTimeStamp + ">" + "newTimeStamp:" + newTimeStamp);
        }
        if(workId > MAX_WORK_ID || workId < 0){
            throw new IllegalArgumentException("workId:" + workId + " is illegal");
        }
        if(lastTimeStamp == newTimeStamp){
            sequence = (sequence + 1) & MAX_SEQUENCE;
            if(sequence == 0){
                newTimeStamp = getNextTimeStamp();
            }
        }else{
            sequence = 0;
        }
        lastTimeStamp = newTimeStamp;
        return (newTimeStamp - initTimeStamp) << TIME_STAMP_LEFT
                | workId << WORK_ID_LEFT
                | sequence;
    }

    private static long getNextTimeStamp() {
        long nextTimeStamp = getNewTimeStamp();
        while (nextTimeStamp <= lastTimeStamp){
            nextTimeStamp = getNewTimeStamp();
        }
        return nextTimeStamp;
    }


    private static long getNewTimeStamp(){
        return System.currentTimeMillis();
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10000; i++) {
            System.out.println(getSnowflake());
        }
    }
}
