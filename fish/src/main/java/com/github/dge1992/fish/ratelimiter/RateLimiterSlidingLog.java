package com.github.dge1992.fish.ratelimiter;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

public class RateLimiterSlidingLog {

    private Integer QPS;

    private TreeMap<Long, Long> treeMap = new TreeMap<>();

    private Long clearTime = 60 * 1000L;

    public RateLimiterSlidingLog(Integer QPS) {
        this.QPS = QPS;
    }

    public boolean tryAcquire(){
        long now = System.currentTimeMillis();
        // 定期清理过期数据
        if(!treeMap.isEmpty() && now - treeMap.firstKey() > clearTime){
            Set<Long> keySet = treeMap.subMap(0L, now - 1000).keySet();
            for (Long key : keySet) {
                treeMap.remove(key);
            }
        }
        // 计算区间次数
        int sum = 0;
        Set<Map.Entry<Long, Long>> entries = treeMap.subMap(now - 1000, now).entrySet();
        for (Map.Entry<Long, Long> entry : entries) {
            sum += entry.getValue();
        }
        // 校验
        if(sum + 1 > QPS){
            return false;
        }
        // 记录
        if(treeMap.containsKey(now)){
            treeMap.compute(now, (k, v) -> v + 1);
        }else {
            treeMap.put(now, 1L);
        }
        return true;
    }

    public static void main(String[] args) throws InterruptedException {
        RateLimiterSlidingLog rateLimiterSlidingLog = new RateLimiterSlidingLog(3);
        for (int i = 0; i < 20; i++) {
            long now = System.currentTimeMillis();
            TimeUnit.MILLISECONDS.sleep(250);
            if(rateLimiterSlidingLog.tryAcquire()){
                System.out.println(now + "正常运行");
            }else{
                System.out.println(now + "限流了");
            }
        }
    }
}
