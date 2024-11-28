package com.github.elliot.flinkdemo.checkpoint;

/**
 * checkpoint算法总结
 * 1.Barrier对齐: Task需要收到所有上游同一个编号的Barrier才开始备份
 *      a. 精准一次: 其中一个上游的Barrier先到达Task, 还有其他上游未发送统一编号的Barrier至Task, 先到达的上游所有后续的的操作都等待
 *      b. 至少一次: 其中一个上游的Barrier先到达Task, 还有其他上游未发送统一编号的Barrier至Task, 先到达的上游所有后续的的操作不等待
 * 2.非Barrier对齐: 一个Task收到第一个Barrier时就开始执行备份，直到最后一个Barrier到达结束备份
 *
 */
public class BarrierConfig {

    public static void main(String[] args) {

    }
}
