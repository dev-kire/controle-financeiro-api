package com.dev_ki.controle_financeiro_api.domain.transaction;

import com.dev_ki.controle_financeiro_api.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.time.LocalDate;
public interface TransactionRepository extends JpaRepository<Transaction, UUID> {
    List<Transaction> findByCategoryUser(User user);

    Optional<Transaction> findByIdAndCategoryUser(UUID id, User user);

    List<Transaction> findByCategoryUserAndType(User user, TransactionType type);

    List<Transaction> findByCategoryUserAndDateBetween(
            User user,
            LocalDate start,
            LocalDate end
    );

    List<Transaction> findByCategoryUserAndCategoryId(
            User user,
            UUID categoryId
    );

    List<Transaction> findByCategoryUserAndTypeAndDateBetween(
            User user,
            TransactionType type,
            LocalDate start,
            LocalDate end
    );
}
