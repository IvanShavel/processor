package com.stock.processor.service;

import com.stock.processor.dto.Request;

public interface RequestService {

    public long startSchedule(Request request);

    public boolean stopSchedule(long scheduleNumber);

}
