package com.stock.processor.schedule;

import com.stock.processor.type.TimeUnit;
import lombok.Data;
import lombok.experimental.Accessors;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Scheduler;

/**
 * Schedule - a timetable which created for every new scheduled requests
 *
 * @author Ivan Shavel
 */
@Data
@Accessors(chain = true)
public class Schedule {
    private long interval;
    private TimeUnit timeUnit;

    /**
     * Event publisher - started new infinity sequence of events by given interval and timeUnit
     */
    private Flux<Long> timer;

    /**
     * Provides pool of executors
     */
    private Scheduler scheduler;
    private String companyName;
}
