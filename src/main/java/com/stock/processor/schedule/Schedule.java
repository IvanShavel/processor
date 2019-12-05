package com.stock.processor.schedule;

import com.stock.processor.type.TimeUnit;
import com.stock.processor.webclient.IExCloudWebClient;
import lombok.Data;
import lombok.experimental.Accessors;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Scheduler;

@Data
@Accessors(chain = true)
public class Schedule {
    private long interval;
    private TimeUnit timeUnit;
    private Flux<Long> timer;
    private Scheduler scheduler;
    private String companyName;
    private IExCloudWebClient client;

}
