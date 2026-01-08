package com.dev_ki.controle_financeiro_api.dto;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public class TransactionCreateDTO {

    @NotNull
    private String description;

    @NotNull
    @Positive
    private BigDecimal amount;

    @NotNull
    private String type;

    @NotNull
    private LocalDate date;

    @NotNull
    private UUID categoryId;

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

    public UUID getCategoryId() {
        return categoryId;
    }
}
