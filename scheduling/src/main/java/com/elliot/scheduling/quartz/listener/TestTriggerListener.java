package com.elliot.scheduling.quartz.listener;

import org.quartz.JobExecutionContext;
import org.quartz.Trigger;
import org.quartz.TriggerListener;

/**
 * 触发器监听器
 */
public class TestTriggerListener implements TriggerListener {

    @Override
    public String getName() {
        return this.getClass().getSimpleName();
    }

    @Override
    public void triggerFired(Trigger trigger, JobExecutionContext jobExecutionContext) {
        System.out.println("触发器触发");
    }

    @Override
    public boolean vetoJobExecution(Trigger trigger, JobExecutionContext jobExecutionContext) {
        System.out.println("触发器判断是否否决执行任务");
        return false;
    }

    @Override
    public void triggerMisfired(Trigger trigger) {
        System.out.println("触发器错过触发");
    }

    @Override
    public void triggerComplete(Trigger trigger, JobExecutionContext jobExecutionContext,
                                Trigger.CompletedExecutionInstruction completedExecutionInstruction) {
        System.out.println("触发结束");
    }
}
