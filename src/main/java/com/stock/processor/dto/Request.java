package com.stock.processor.dto;

import com.stock.processor.type.TimeUnit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * Request - provides information which is necessary for sending schedule requests
 *
 * @author Ivan Shavel
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Request {

    /**
     * company name
     */
    private List<String> companyName;

    /**
     * time interval between requests
     */
    private int interval;

    /**
     * time unit for interval between requests
     */
    private TimeUnit timeUnit;
}
