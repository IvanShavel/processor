package com.stock.processor.schedule;

import com.stock.processor.model.Company;
import com.stock.processor.repository.CompanyRepository;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Signal;

import java.time.LocalDateTime;

/**
 * ScheduleManager - provides methods for work with schedule
 *
 * @author Ivan Shavel
 */
@Data
@Accessors(chain = true)
@Slf4j
public class ScheduleManager {
    private final Schedule schedule;
    private final CompanyRepository companyRepository;
    private final String serverUrl = "https://cloud.iexapis.com/stable/stock/";
    private final String token = "pk_51dc0716d4f646d4bb8cad7e57f5bf4d";

    /**
     * https://cloud.iexapis.com/stable/stock/aapl/quote/latestPrice?token=pk_51dc0716d4f646d4bb8cad7e57f5bf4d
     * https://cloud.iexapis.com/stable/stock/aapl/logo?token=pk_51dc0716d4f646d4bb8cad7e57f5bf4d
     * all
     * https://cloud.iexapis.com/stable/ref-data/symbols?token=pk_51dc0716d4f646d4bb8cad7e57f5bf4d
     */

    public void start() {
        try {
            schedule.getTimer()
                    .publishOn(schedule.getScheduler())
                    .doOnEach(this::getInfo).blockLast();
        } catch (Exception e) {
            log.info("STOPPED " + " company is " + schedule.getCompanyName()
                    + " time is " + LocalDateTime.now()
                    + " scheduler " + schedule.getScheduler()
                    + " time interval " + schedule.getInterval()
                    + " time unit " + schedule.getTimeUnit());
        }
    }

    private void getInfo(Signal<Long> po) {
        double stockPrice = WebClient.create(serverUrl)
                .get()
                .uri(schedule.getCompanyName() + "/quote/latestPrice?token=" + token)
                .retrieve()
                .bodyToMono(Double.class)
                .block();

        String companyLogo = WebClient.create(serverUrl)
                .get()
                .uri(schedule.getCompanyName() + "/logo?token=" + token)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        Company company = new Company()
                .setName(schedule.getCompanyName())
                .setPrice(String.valueOf(stockPrice))
                .setLogo(companyLogo);
        log.info(String.valueOf(company));
        companyRepository.save(company);
    }
}
