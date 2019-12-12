package com.stock.processor.service;

import com.stock.processor.dto.Request;

/**
 * RequestService - provides service method for starting scheduled requests
 *
 * @author Ivan Shavel
 */
public interface RequestService {

    /**
     * starts schedule
     *
     * @param request - contains necessary information to start scheduled requests
     */
    void startSchedule(Request request);
}
