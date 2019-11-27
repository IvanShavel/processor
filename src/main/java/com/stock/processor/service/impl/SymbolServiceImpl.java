package com.stock.processor.service.impl;

import com.stock.processor.dto.Symbol;
import com.stock.processor.service.SymbolService;
import com.stock.processor.webclient.IExCloudWebClient;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SymbolServiceImpl implements SymbolService {
    private final IExCloudWebClient client;
    @Value("${iExCloudToken}")
    private String token;
    @Value("${iExCloudSymbols}")
    private String url;

    @Override
    public List<Symbol> getAll() {
        return client
                .getWebClient()
                .get()
                .uri(url + token)
                .retrieve()
                .bodyToFlux(Symbol.class)
                .collectList()
                .block();
    }

}
