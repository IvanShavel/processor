package com.stock.processor.controller;

import com.stock.processor.dto.Symbol;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import reactor.core.publisher.Flux;

import java.util.List;

@RequestMapping(path = "/symbol/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public interface SymbolController {

    @GetMapping("/all")
    public List<Symbol> getPositions();

}
