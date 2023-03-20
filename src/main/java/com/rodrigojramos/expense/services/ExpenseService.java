package com.rodrigojramos.expense.services;


import com.rodrigojramos.expense.dto.ExpenseDTO;
import com.rodrigojramos.expense.entities.Expense;
import com.rodrigojramos.expense.repositories.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository repository;

    @Transactional(readOnly = true)
    public ExpenseDTO findById(Long id) {
        Optional<Expense> result = repository.findById(id);
        Expense expense = result.get();
        ExpenseDTO dto = new ExpenseDTO(expense);
        return dto;
    }

    @Transactional(readOnly = true)
    public List<ExpenseDTO> findAll() {
        List<Expense> result = repository.findAll();
        return result.stream().map(x -> new ExpenseDTO(x)).toList();
    }

    @Transactional
    public ExpenseDTO insert(ExpenseDTO dto) {
        Expense entity = new Expense();
        copyDtoToEntity(dto, entity);
        entity = repository.save(entity);
        return new ExpenseDTO(entity);
    }

    @Transactional
    public ExpenseDTO update(Long id, ExpenseDTO dto) {
        Expense entity = repository.getReferenceById(id);
        copyDtoToEntity(dto, entity);
        entity = repository.save(entity);
        return new ExpenseDTO(entity);
    }

    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }

    private void copyDtoToEntity(ExpenseDTO dto, Expense entity) {
        entity.setDescription(dto.getDescription());
        entity.setAmount(dto.getAmount());
        entity.setDate(dto.getDate());
    }
}
