package com.stock.processor.service;

import com.stock.processor.dto.Symbol;
import reactor.core.publisher.Flux;

import java.util.List;

public interface SymbolService {

    List<Symbol> getAll();

}
