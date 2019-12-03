package com.stock.processor.controller;

import com.stock.processor.dto.Request;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(path = "/schedule")
public interface RequestController {

    @ApiOperation(value = "start")
    @PostMapping("/start")
    public long startSchedule(Request request);

    @ApiOperation(value = "stop")
    @GetMapping("/stop")
    public boolean stopSchedule(long scheduleNumber);

}
