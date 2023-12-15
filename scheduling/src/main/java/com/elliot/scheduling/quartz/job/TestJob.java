package com.elliot.scheduling.quartz.job;

import lombok.Data;
import org.quartz.*;

import java.time.LocalDateTime;

/**
 * 测试任务 - 打印当前时间
 */
@PersistJobDataAfterExecution
@Data
public class TestJob implements Job {

    // 同名存在覆盖的情况
    private String message;

    private Integer count;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {

        System.out.println(LocalDateTime.now() + "开始执行了");

        // get JobDetail's Context
        JobDataMap jobDetailJobDataMap = context.getJobDetail().getJobDataMap();
        String jobDetailMessage = jobDetailJobDataMap.getString("message");
        System.out.println("jobDetailMessage:" + jobDetailMessage);
        // get Trigger's context
        JobDataMap triggerJobDataMap = context.getTrigger().getJobDataMap();
        String triggerMessage = triggerJobDataMap.getString("message");
        System.out.println("triggerMessage:" + triggerMessage);

        System.out.println("message is " + message);
        System.out.println("count is " + count);
        count++;
        context.getJobDetail().getJobDataMap().put("count", count);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("job run time is " + context.getJobRunTime());
        System.out.println("job fire time is " + context.getFireTime());
        System.out.println("job next fire time is " + context.getNextFireTime());
        System.out.println(LocalDateTime.now() + "执行结束了");
    }
}
