package com.stock.processor.dto;

import com.stock.processor.type.TimeUnit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Request {
    private List<String> companyName;
    private int interval;
    private TimeUnit timeUnit;

}
