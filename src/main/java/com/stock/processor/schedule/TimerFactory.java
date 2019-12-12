package com.stock.processor.schedule;

import com.stock.processor.type.TimeUnit;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.time.Duration;

/**
 * TimerFactory - factory for creating timer by time interval and time unit
 *
 * @author Ivan Shavel
 */
@Component
public class TimerFactory {

    public Flux<Long> getScheduler(long time, TimeUnit timeUnit) {
        switch (timeUnit) {
            case SECOND:
                return Flux.interval(Duration.ofSeconds(time));
            case MINUTE:
                return Flux.interval(Duration.ofMinutes(time));
            case HOUR:
                return Flux.interval(Duration.ofHours(time));
            default:
                throw new UnsupportedOperationException("Unknown type of time unit");
        }
    }

}
