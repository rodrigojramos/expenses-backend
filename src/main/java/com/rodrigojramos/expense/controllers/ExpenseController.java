package com.rodrigojramos.expense.controllers;

import com.rodrigojramos.expense.dto.ExpenseDTO;
import com.rodrigojramos.expense.services.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value ="/expenses")
public class ExpenseController {

    @Autowired
    private ExpenseService service;

    @GetMapping
    public List<ExpenseDTO> findAll() {
        return service.findAll();
    }
}
