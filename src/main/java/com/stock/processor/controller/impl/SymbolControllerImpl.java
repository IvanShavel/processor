package com.stock.processor.controller.impl;

import com.stock.processor.controller.SymbolController;
import com.stock.processor.dto.Symbol;
import com.stock.processor.model.Company;
import com.stock.processor.repository.CompanyRepository;
import com.stock.processor.service.SymbolService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class SymbolControllerImpl implements SymbolController {
    private final SymbolService symbolService;

    @Override
    public List<Symbol> getAllCompanies() {
        return symbolService.getAll();
    }
}
