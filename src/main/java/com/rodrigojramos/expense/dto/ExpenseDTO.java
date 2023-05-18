package com.rodrigojramos.expense.dto;

import com.rodrigojramos.expense.entities.Expense;
import com.rodrigojramos.expense.projections.ExpenseProjection;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class ExpenseDTO {

    private Long id;
    @Size(min = 3, message = "Descrição precisa ter no mínimo 3 caracteres")
    @NotBlank(message = "Campo requerido")
    private String description;
    @Positive(message = "O valor deve ser positivo")
    private Double amount;
    @PastOrPresent(message = "A data não pode ser futura")
    private LocalDate date;

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

    public ExpenseDTO(ExpenseProjection projection) {
        id = projection.getId();
        description = projection.getDescription();
        amount = projection.getAmount();
        date = projection.getDate();
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
