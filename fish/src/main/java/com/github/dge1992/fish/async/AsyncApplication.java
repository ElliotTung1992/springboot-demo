package com.github.dge1992.fish.async;

/**
 * @author dge
 * @version 1.0
 * @date 2021-01-25 14:46
 */
public class AsyncApplication {

    public static void main(String[] args) {
        /**
         *
         * 异步化的好处
         *
         * 1. 容错性
         *  服务有A和B两个接口, 接口A因故障超时
         *  同步下的情况: 因接口A故障导致大量线程阻塞，进而影响接口B,最后导致整个服务崩了
         *  异步下的情况: 因实现异步化，只有接口A对应的线程池影响，接口B对应的线程池正常工作
         *  -- 即利用异步框架支持线程池切换的特性实现服务/接口隔离，提高系统的高可用。
         *
         * 2. 加大吞吐
         *  同步下的情况: Tomcat的线程被用于具体的业务
         *  异步下的情况: Tomcat的线程用于接收请求调度，由具体的异步线程池执行具体的业务
         *
         */
    }
}
