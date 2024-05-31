package com.github.dge1992.fish.ratelimiter;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 滑动窗口算法
 */
public class RateLimiterSlidingWindow {

    /**
     * 阀值
     */
    private Integer QPS;
    /**
     * 时间窗口大小
     */
    private Long windowSize;
    /**
     * 子窗口
     */
    private Integer windowCount;
    /**
     * 窗口列表
     */
    private WindowInfo[] windowInfoArray;

    RateLimiterSlidingWindow(Integer QPS, Long windowSize, Integer windowCount){
        this.QPS = QPS;
        this.windowSize = windowSize;
        this.windowCount = windowCount;
        long now = System.currentTimeMillis();
        windowInfoArray = new WindowInfo[windowCount];
        for (int i = 0; i < windowCount; i++) {
            windowInfoArray[i] = new WindowInfo(now, new AtomicInteger(0));
        }
    }

    private static class WindowInfo{

        private Long currentTime;
        private AtomicInteger num;

        public WindowInfo(Long currentTime, AtomicInteger num) {
            this.currentTime = currentTime;
            this.num = num;
        }

        public Long getCurrentTime() {
            return currentTime;
        }

        public void setCurrentTime(Long currentTime) {
            this.currentTime = currentTime;
        }

        public AtomicInteger getNum() {
            return num;
        }

        public void setNum(AtomicInteger num) {
            this.num = num;
        }
    }

    public synchronized boolean tryAcquire(){
        // 当前时间
        long now = System.currentTimeMillis();
        // 计算当前实现下标
        int index = (int)((now % windowSize) / (windowSize / windowCount));
        int sum = 0;
        for (int i = 0; i < windowCount; i++) {
            WindowInfo windowInfo = windowInfoArray[i];
            // 重置
            if((now - windowInfo.getCurrentTime()) > windowSize){
                windowInfo.setCurrentTime(now);
                windowInfo.getNum().set(0);
            }
            if(index == i && windowInfo.getNum().get() <= QPS){
                windowInfo.getNum().incrementAndGet();
            }
            sum += windowInfo.getNum().get();
        }
        return sum <= QPS;
    }

    public static void main(String[] args) throws InterruptedException {
        RateLimiterSlidingWindow rateLimiterSlidingWindow =
                new RateLimiterSlidingWindow(2, 1000L, 10);
        for (int i = 0; i < 20; i++) {
            TimeUnit.MILLISECONDS.sleep(300L);
            if(rateLimiterSlidingWindow.tryAcquire()){
                System.out.println("成功");
            }else {
                System.out.println("失败");
            }
        }
    }
}
