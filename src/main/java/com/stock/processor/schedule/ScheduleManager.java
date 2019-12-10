package com.stock.processor.schedule;

import com.stock.processor.model.Company;
import com.stock.processor.repository.CompanyRepository;
import com.stock.processor.type.TimeUnit;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
@Slf4j
public class ScheduleManager {
    private final Schedule schedule;
    private final CompanyRepository companyRepository;

    public void start() {
        try {
            schedule.getTimer()
                    .publishOn(schedule.getScheduler())
                    .doOnEach(po -> {
                      final   double price = WebClient.create("https://sandbox.iexapis.com/stable/")
                                .get()
                                .uri("stock/" + schedule.getCompanyName() + "/price?token=" + "Tsk_a29d14855d4c4ac5b8bc51a86ea05b44")
                                .retrieve()
                                .bodyToMono(Double.class)
                                .block();

                                System.out.println(price
                                        + " company is " + schedule.getCompanyName()
                                        + " time is " + LocalDateTime.now()
                                        + " scheduler " + schedule.getScheduler()
                                        + " time interval " + schedule.getInterval()
                                        + " time unit " + schedule.getTimeUnit()
                                );
                        Company company = new Company()
                                .setName(schedule.getCompanyName())
                                .setPrice(price+"")
                                .setLogo("logo");
                           //     companyRepository.save(company);
                            }
                    ).blockLast();
        } catch (Exception e) {
            log.info("STOPPED " + " company is " + schedule.getCompanyName()
                    + " time is " + LocalDateTime.now()
                    + " scheduler " + schedule.getScheduler()
                    + " time interval " + schedule.getInterval()
                    + " time unit " + schedule.getTimeUnit());
        }

    }

    public boolean stop(long interval, TimeUnit timeUnit) {
        boolean isStopped = false;
        if (schedule.getInterval() == interval && schedule.getTimeUnit().equals(timeUnit)) {
            schedule.getScheduler().dispose();
            isStopped = true;
        }
        return isStopped;
    }

}
