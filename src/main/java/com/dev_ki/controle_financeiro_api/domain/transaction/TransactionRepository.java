package com.dev_ki.controle_financeiro_api.domain.transaction;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TransactionRepository extends JpaRepository<Transaction, UUID> {

    List<Transaction> findByType(TransactionType type);
    List<Transaction> findByCategoryId(UUID categoryId);
}
