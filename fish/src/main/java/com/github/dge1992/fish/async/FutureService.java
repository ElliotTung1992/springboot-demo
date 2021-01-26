package com.github.dge1992.fish.async;

/**
 * @author dge
 * @version 1.0
 * @date 2021-01-25 11:05
 *
 * Future的缺点
 * 1. 无法方便得得知任务合适结束
 * 2. 无法方便得获取任务的结果
 * 3. 获取任务结果时会阻塞主线程
 *
 */
public interface FutureService {

    /**
     * 测试Feture
     * @author dge
     * @date 2021-01-25 11:06
     */
    void testFuture();

}
