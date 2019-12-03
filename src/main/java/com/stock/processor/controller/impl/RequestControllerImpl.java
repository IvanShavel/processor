package com.stock.processor.controller.impl;

import com.stock.processor.controller.RequestController;
import com.stock.processor.dto.Request;
import com.stock.processor.service.RequestService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class RequestControllerImpl implements RequestController {
    private final RequestService requestService;

    @Override
    public long startSchedule(Request request) {
        return requestService.startSchedule(request);
    }

    @Override
    public boolean stopSchedule(long scheduleNumber) {
        return false;
    }
}
