package com.rodrigojramos.expense.repositories;

import com.rodrigojramos.expense.entities.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
}
