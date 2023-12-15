package com.elliot.scheduling.quartz.listener;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;

/**
 * JobListener
 */
public class TestJobListener implements JobListener {

    @Override
    public String getName() {
        return this.getClass().getSimpleName();
    }

    @Override
    public void jobToBeExecuted(JobExecutionContext jobExecutionContext) {
        System.out.println("jobToBeExecuted 任务执行前");
    }

    @Override
    public void jobExecutionVetoed(JobExecutionContext jobExecutionContext) {
        System.out.println("jobExecutionVetoed 任务执行否决");
    }

    @Override
    public void jobWasExecuted(JobExecutionContext jobExecutionContext, JobExecutionException e) {
        System.out.println("jobWasExecuted 任务执行结束");
    }
}
