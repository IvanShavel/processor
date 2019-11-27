package com.stock.processor.webclient;

import org.springframework.web.reactive.function.client.WebClient;

public interface IExCloudWebClient {
    WebClient getWebClient();
}
