package com.rodrigojramos.expense.controllers;

import com.rodrigojramos.expense.dto.ExpenseDTO;
import com.rodrigojramos.expense.services.ExpenseService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value ="/expenses")
public class ExpenseController {

    @Autowired
    private ExpenseService service;

    @GetMapping
    public ResponseEntity<List<ExpenseDTO>> findAll() {
        List<ExpenseDTO> dto = service.findAll();
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<ExpenseDTO> insert(@RequestBody ExpenseDTO dto) {
        dto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }
}
