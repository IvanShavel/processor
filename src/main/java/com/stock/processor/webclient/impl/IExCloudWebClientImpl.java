package com.stock.processor.webclient.impl;

import com.stock.processor.webclient.IExCloudWebClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class IExCloudWebClientImpl implements IExCloudWebClient {

    private WebClient webClient;

    public IExCloudWebClientImpl(@Value("${iExCloudServer}") String server) {
        webClient = WebClient.create(server);
    }

    public WebClient getWebClient() {
        return webClient;
    }

}
