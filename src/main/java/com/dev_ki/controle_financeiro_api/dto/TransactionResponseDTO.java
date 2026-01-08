package com.dev_ki.controle_financeiro_api.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class TransactionResponseDTO {

    private UUID id;
    private String description;
    private BigDecimal amount;
    private String type;
    private LocalDate date;
    private UUID categoryId;
    private String categoryName;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public UUID getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(UUID categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public TransactionResponseDTO(
            UUID id,
            String description,
            BigDecimal amount,
            String type,
            LocalDate date,
            UUID categoryId,
            String categoryName
    ) {
        this.id = id;
        this.description = description;
        this.amount = amount;
        this.type = type;
        this.date = date;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }
}
