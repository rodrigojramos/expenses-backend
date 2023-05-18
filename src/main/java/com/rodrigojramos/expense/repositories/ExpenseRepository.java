package com.rodrigojramos.expense.repositories;

import com.rodrigojramos.expense.entities.Expense;
import com.rodrigojramos.expense.projections.ExpenseProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    @Query(nativeQuery = true, value = """
            SELECT *
            FROM tb_expense
            WHERE  EXTRACT (MONTH FROM date)  = :expenseMonth
            AND EXTRACT (YEAR FROM date) = :expenseYear
            """)
    Page<ExpenseProjection> searchByMonthAndYear(Integer expenseMonth, Integer expenseYear, Pageable pageable);

    @Query("SELECT obj FROM Expense obj WHERE UPPER(obj.description) LIKE UPPER(CONCAT('%', :description, '%'))")
    Page<Expense> searchByName(String description, Pageable pageable);
}
