package com.stock.processor.schedule;

import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;

import java.util.Date;
import java.util.concurrent.ScheduledFuture;

public class DynamicSchedule implements Trigger {

    private TaskScheduler scheduler;
    private Runnable task;
    private ScheduledFuture<?> future;
    private int delay;

    public DynamicSchedule(TaskScheduler scheduler, Runnable task, int delay) {
        this.scheduler = scheduler;
        this.task = task;
        reset(delay);
    }

    public void reset(int delay) {
        if (future != null) {
            System.out.println("Cancelling task...");
            future.cancel(true);
        }
        this.delay = delay;
        System.out.println("Starting task...");
        future = scheduler.schedule(task, this);
    }

    @Override
    public Date nextExecutionTime(TriggerContext triggerContext) {
        Date lastTime = triggerContext.lastActualExecutionTime();
        Date nextExecutionTime = (lastTime == null)
                ? new Date()
                : new Date(lastTime.getTime() + delay);
        System.out.println("DynamicSchedule -- delay: " + delay +
                ", lastActualExecutionTime: " + lastTime +
                "; nextExecutionTime: " + nextExecutionTime);
        return nextExecutionTime;
    }

}