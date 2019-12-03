package com.stock.processor.schedule;

import com.stock.processor.dto.Symbol;
import com.stock.processor.webclient.IExCloudWebClient;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class RequestSchedule {
    private final String schedule = "*/5 * * * * *";
    private final IExCloudWebClient client;
    @Value("${iExCloudToken}")
    private String token;
    @Value("${iExCloudPrice}")
    private String url;


    @Scheduled(cron = schedule)
    public void sendRequest() {

        System.out.println(
                client
                        .getWebClient()
                        .get()
                        .uri(url + token)
                        .retrieve()
                        .bodyToMono(Integer.class)
                        .block()
        );


    }

}
