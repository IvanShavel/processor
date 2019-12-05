package com.stock.processor.service;

import com.stock.processor.dto.Request;

public interface RequestService {

    public void startSchedule(Request request);

    public boolean stopSchedule(Request request);

}
