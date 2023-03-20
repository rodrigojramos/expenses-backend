package com.rodrigojramos.expense.dto;

import com.rodrigojramos.expense.entities.Expense;

import java.time.LocalDate;

public class ExpenseDTO {

    private Long id;
    private String description;
    private Double amount;
    private LocalDate date;

    public ExpenseDTO() {
    }

    public ExpenseDTO(Long id, String description, Double amount, LocalDate date) {
        this.id = id;
        this.description = description;
        this.amount = amount;
        this.date = date;
    }

    public ExpenseDTO(Expense entity) {
        id = entity.getId();
        description = entity.getDescription();
        amount = entity.getAmount();
        date = entity.getDate();
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public Double getAmount() {
        return amount;
    }

    public LocalDate getDate() {
        return date;
    }
}
