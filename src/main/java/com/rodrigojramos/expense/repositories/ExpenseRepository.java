package com.rodrigojramos.expense.repositories;

import com.rodrigojramos.expense.entities.Expense;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    @Query("SELECT obj FROM Expense obj WHERE UPPER(obj.description) LIKE UPPER(CONCAT('%', :description, '%'))")
    Page<Expense> searchByName(String description, Pageable pageable);
}
