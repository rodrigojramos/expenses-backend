package com.rodrigojramos.expense.projections;

import java.time.LocalDate;

public interface ExpenseProjection {

    Long getId();
    Double getAmount();
    LocalDate getDate();
    String getDescription();
}
