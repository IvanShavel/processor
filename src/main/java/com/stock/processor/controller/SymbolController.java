package com.stock.processor.controller;

import com.stock.processor.dto.Symbol;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping(path = "/symbol/")
public interface SymbolController {

    @GetMapping("/all")
    public List<Symbol> getPositions();

}
