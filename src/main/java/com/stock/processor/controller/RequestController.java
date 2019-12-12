package com.stock.processor.controller;

import com.stock.processor.dto.Request;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

/**
 * RequestController - provides end-point for starting scheduled requests
 *
 * @author Ivan Shavel
 */
@RequestMapping(path = "/schedule")
public interface RequestController {

    @ApiOperation(value = "Start schedule")
    @PostMapping("/start")
    public void startSchedule(@Valid @RequestBody Request request);

}
