package com.stock.processor.schedule;

import com.stock.processor.webclient.IExCloudWebClient;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@RequiredArgsConstructor
@Component
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class RequestSchedule {
    private final String schedule = "*/5 * * * * *";
    private final IExCloudWebClient client;
    @Value("${iExCloudToken}")
    private String token;
    @Value("${iExCloudPrice}")
    private String url;

    private int time;


    @Scheduled(cron = schedule)
    public void sendRequest() {
        System.out.println(
                client
                        .getWebClient()
                        .get()
                        .uri(url + token)
                        .retrieve()
                        .bodyToMono(Double.class)
                        .block()
        );
    }


    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
