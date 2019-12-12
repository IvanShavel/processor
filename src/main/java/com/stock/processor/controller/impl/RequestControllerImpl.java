package com.stock.processor.controller.impl;

import com.stock.processor.controller.RequestController;
import com.stock.processor.dto.Request;
import com.stock.processor.service.RequestService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

/**
 * RequestControllerImpl
 *
 * @author Ivan Shavel
 */
@RestController
@AllArgsConstructor
public class RequestControllerImpl implements RequestController {
    private final RequestService requestService;

    @Override
    public void startSchedule(Request request) {
        requestService.startSchedule(request);
    }


}