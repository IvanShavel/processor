package com.stock.processor.service;

import com.stock.processor.dto.Request;

public interface RequestService {

    void startSchedule(Request request);

    boolean stopSchedule(Request request);

}
