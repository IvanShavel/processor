package com.stock.processor.schedule;

import com.stock.processor.type.TimeUnit;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
public class ScheduleManager {
    private final Schedule schedule;

    public void start() {
        schedule.getTimer()
                .publishOn(schedule.getScheduler())
                .doOnEach(po -> {
                            System.out.println(WebClient.create("https://sandbox.iexapis.com/stable/")
                                    .get()
                                    .uri("stock/" + schedule.getCompanyName() + "/price?token=" + "Tsk_a29d14855d4c4ac5b8bc51a86ea05b44")
                                    .retrieve()
                                    .bodyToMono(Double.class)
                                    .block()
                                    +" company is "+schedule.getCompanyName()
                                    +" time is "+ LocalDateTime.now()
                            );
                        }
                ).blockLast();
    }

    public void stop(long interval, TimeUnit timeUnit){
        if (schedule.getInterval() == interval && schedule.getTimeUnit().equals(timeUnit)){
            schedule.getScheduler().dispose();
        }
    }

}
