package com.rodrigojramos.expense.services;


import com.rodrigojramos.expense.dto.ExpenseDTO;
import com.rodrigojramos.expense.entities.Expense;
import com.rodrigojramos.expense.projections.ExpenseProjection;
import com.rodrigojramos.expense.repositories.ExpenseRepository;
import com.rodrigojramos.expense.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository repository;

    @Transactional(readOnly = true)
    public ExpenseDTO findById(Long id) {
        Expense expense = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Recurso não encontrado"));
        return new ExpenseDTO(expense);
    }

    @Transactional(readOnly = true)
    public Page<ExpenseDTO> findAll(String description, Pageable pageable) {
        Page<Expense> result = repository.searchByName(description, pageable);
        return result.map(x -> new ExpenseDTO(x));
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
        try{
            Expense entity = repository.getReferenceById(id);
            copyDtoToEntity(dto, entity);
            entity = repository.save(entity);
            return new ExpenseDTO(entity);
        }
        catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Recurso não encontrado");
        }

    }

    @Transactional
    public void delete(Long id) {
        try {
            repository.deleteById(id);
        }
        catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Recurso não encontrado");
        }
    }

    @Transactional(readOnly = true)
    public Page<ExpenseDTO> findByMonthAndYear(Integer expenseMonth, Integer expenseYear, String description, Pageable pageable ) {
        Page<Expense> result = repository.searchExpenseByMonthYearAndDescription(expenseMonth, expenseYear, description, pageable);
        return result.map(x -> new ExpenseDTO(x));
    }

    private void copyDtoToEntity(ExpenseDTO dto, Expense entity) {
        entity.setDescription(dto.getDescription());
        entity.setAmount(dto.getAmount());
        entity.setDate(dto.getDate());
    }
}
