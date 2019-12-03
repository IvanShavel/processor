package com.stock.processor.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Symbol {
    private String symbol;
    private String name;
    private Date date;
    private String type;
    private String iexId;
    private String region;
    private String currency;
    private boolean isEnabled;

}



