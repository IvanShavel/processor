package com.stock.processor.service.impl;

import com.stock.processor.dto.Request;
import com.stock.processor.dto.Symbol;
import com.stock.processor.schedule.RequestSchedule;
import com.stock.processor.service.RequestService;
import com.stock.processor.service.SymbolService;
import com.stock.processor.webclient.IExCloudWebClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.security.auth.message.callback.SecretKeyCallback;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class RequestServiceImpl implements RequestService {
    private final IExCloudWebClient client;
    @Value("${iExCloudToken}")
    private String token;
    @Value("${iExCloudSymbols}")
    private String url;
    @Value("${iExCloudPrice}")
    private String price;
    private List schedules = new ArrayList();


    @Override
    public long startSchedule(Request request) {
        RequestSchedule schedule = new RequestSchedule(client,token, price);
        schedules.add(schedule);
        schedule.sendRequest();
        return 0;
    }

    @Override
    public boolean stopSchedule(long scheduleNumber) {
        return false;
    }
}
