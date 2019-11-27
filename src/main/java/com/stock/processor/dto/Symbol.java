package com.stock.processor.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Symbol {
    String symbol;
    String name;
    Date date;
    String type;
    String iexId;
    String region;
    String currency;
    boolean isEnabled;

}



