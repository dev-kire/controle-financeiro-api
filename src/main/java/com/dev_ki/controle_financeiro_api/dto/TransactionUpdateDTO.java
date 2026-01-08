package com.dev_ki.controle_financeiro_api.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.security.core.parameters.P;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TransactionUpdateDTO {

    @NotNull
    private String description;

    @NotNull
    @Positive
    private BigDecimal amount;

    @NotNull
    private String type;

    @NotNull
    private LocalDate date;

    public String getDescription() {
        return description;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getType() {
        return type;
    }

    public LocalDate getDate() {
        return date;
    }
}
