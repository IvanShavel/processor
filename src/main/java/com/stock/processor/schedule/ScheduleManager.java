package com.stock.processor.schedule;

import com.stock.processor.model.Company;
import com.stock.processor.repository.CompanyRepository;
import com.stock.processor.type.TimeUnit;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
@Slf4j
public class ScheduleManager {
    private final Schedule schedule;
    private final CompanyRepository companyRepository;
    private final String prefix;
    private final String token;
    private final String logo;
    private final String price;


    //https://cloud.iexapis.com/stable/stock/aapl/quote/latestPrice?token=pk_51dc0716d4f646d4bb8cad7e57f5bf4d

    public void start() {
        try {
            schedule.getTimer()
                    .publishOn(schedule.getScheduler())
                    .doOnEach(po -> {
                                double stockPrice = schedule.getClient().getWebClient()
                                        .get()
                                        .uri(prefix + schedule.getCompanyName() + price + token)
                                        .retrieve()
                                        .bodyToMono(Double.class)
                                        .block();

                                String companyLogo = schedule.getClient().getWebClient()
                                        .get()
                                        .uri(prefix + schedule.getCompanyName() + logo + token)
                                        .retrieve()
                                        .bodyToMono(String.class)
                                        .block();

                                Company company = new Company()
                                        .setName(schedule.getCompanyName())
                                        .setPrice(stockPrice + "")
                                        .setLogo(companyLogo);
                        System.out.println(company);
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


}
