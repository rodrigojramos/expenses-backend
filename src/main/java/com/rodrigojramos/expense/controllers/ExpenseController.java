package com.rodrigojramos.expense.controllers;

import com.rodrigojramos.expense.dto.ExpenseDTO;
import com.rodrigojramos.expense.services.ExpenseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @GetMapping(value = "/{id}")
    public ResponseEntity<ExpenseDTO> findById(@PathVariable Long id) {
        ExpenseDTO dto = service.findById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<Page<ExpenseDTO>> findAll(
            @RequestParam(name = "description", defaultValue = "") String description,
            Pageable pageable) {
        Page<ExpenseDTO> dto = service.findAll(description, pageable);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<ExpenseDTO> insert(@Valid @RequestBody ExpenseDTO dto) {
        dto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ExpenseDTO> update(@PathVariable Long id, @Valid @RequestBody ExpenseDTO dto) {
        dto = service.update(id, dto);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
