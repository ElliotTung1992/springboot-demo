package com.github.dge1992.fish.ratelimiter;

import java.util.Collection;
import java.util.Set;
import java.util.TreeMap;

public class RateLimiterSlidingLog implements IRateLimiter {

    private Integer perSecondCount = 2;

    private static TreeMap<Long, Integer> treeMap = new TreeMap<>();

    private Long startTime;

    public RateLimiterSlidingLog(Long startTime){
        this.startTime = startTime;
    }

    @Override
    public synchronized boolean acquire() {
        long currentTime = System.currentTimeMillis();
        if(!treeMap.isEmpty() && currentTime - treeMap.firstKey() > 5 * 1000L){
            Set<Long> keySet = treeMap.subMap(0L, currentTime - 1000).keySet();
            for (Long aLong : keySet) {
                treeMap.remove(aLong);
            }
        }
        int sum = 0;
        Collection<Integer> values = treeMap.subMap(currentTime - 1000, currentTime).values();
        for (Integer value : values) {
            sum += value;
        }
        if(sum >= perSecondCount){
            return false;
        }
        if(treeMap.containsKey(currentTime)){
            treeMap.compute(currentTime, (k,v) -> v + 1);
        }else{
            treeMap.put(currentTime, 1);
        }
        return true;
    }
}
