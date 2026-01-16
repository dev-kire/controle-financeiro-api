package com.dev_ki.controle_financeiro_api.domain.transaction;

import com.dev_ki.controle_financeiro_api.domain.category.Category;
import com.dev_ki.controle_financeiro_api.domain.category.CategoryRepository;
import com.dev_ki.controle_financeiro_api.domain.user.User;
import com.dev_ki.controle_financeiro_api.domain.user.UserRepository;
import com.dev_ki.controle_financeiro_api.dto.TransactionCreateDTO;
import com.dev_ki.controle_financeiro_api.dto.TransactionResponseDTO;
import com.dev_ki.controle_financeiro_api.dto.TransactionUpdateDTO;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    public TransactionService(
            TransactionRepository transactionRepository,
            CategoryRepository categoryRepository,
            UserRepository userRepository
    ) {
        this.transactionRepository = transactionRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
    }

    private User getAuthenticatedUser() {
        String email = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }

    public TransactionResponseDTO create(TransactionCreateDTO dto) {
        User user = getAuthenticatedUser();

        Category category = categoryRepository.findById(dto.getCategoryId())
                .filter(c -> c.getUser().getId().equals(user.getId()))
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

        Transaction transaction = new Transaction();
        transaction.setDescription(dto.getDescription());
        transaction.setAmount(dto.getAmount());
        transaction.setType(TransactionType.valueOf(dto.getType()));
        transaction.setCategory(category);
        transaction.setDate(dto.getDate());

        Transaction saved = transactionRepository.save(transaction);
        return toResponse(saved);
    }

    public List<TransactionResponseDTO> list() {
        User user = getAuthenticatedUser();

        return transactionRepository.findByCategoryUser(user)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public List<TransactionResponseDTO> filter(
            String type,
            LocalDate from,
            LocalDate to,
            UUID categoryId
    ) {
      User user = getAuthenticatedUser();
      List<Transaction> result;

        if (type != null && from != null && to != null) {
            result = transactionRepository.findByCategoryUserAndTypeAndDateBetween(
                    user,
                    TransactionType.valueOf(type),
                    from,
                    to
            );
      }
        else if (type != null) {
            result = transactionRepository.findByCategoryUserAndType(user, TransactionType.valueOf(type)
            );
        } else if (from != null && to != null) {
            result = transactionRepository.findByCategoryUserAndDateBetween(
                    user,
                    from,
                    to
            );
        } else if (categoryId != null) {
            result = transactionRepository.findByCategoryUserAndCategoryId(
                    user,
                    categoryId
            );
        }
        else {
            result = transactionRepository.findByCategoryUser(user);
        }
        return result.stream()
                .map(this :: toResponse)
                .toList();
    }

    public TransactionResponseDTO findById(UUID id) {
        User user = getAuthenticatedUser();

        Transaction transaction = transactionRepository.findByIdAndCategoryUser(id, user)
                .orElseThrow(() -> new RuntimeException("Transação não encontrada"));

        return toResponse(transaction);
    }

    public TransactionResponseDTO update(UUID id, TransactionUpdateDTO dto) {
        User user = getAuthenticatedUser();

        Transaction transaction = transactionRepository.findByIdAndCategoryUser(id, user)
                .orElseThrow(() -> new RuntimeException("Transação não encontrada"));

        transaction.setDescription(dto.getDescription());
        transaction.setAmount(dto.getAmount());
        transaction.setType(TransactionType.valueOf(dto.getType()));
        transaction.setDate(dto.getDate());

        Transaction updated = transactionRepository.save(transaction);
        return toResponse(updated);
    }

    public void delete(UUID id) {
        User user = getAuthenticatedUser();

        Transaction transaction = transactionRepository.findByIdAndCategoryUser(id, user)
                .orElseThrow(() -> new RuntimeException("Transação não encontrada"));

        transactionRepository.delete(transaction);
    }

    private TransactionResponseDTO toResponse(Transaction transaction) {
        return new TransactionResponseDTO(
                transaction.getId(),
                transaction.getDescription(),
                transaction.getAmount(),
                transaction.getType().name(),
                transaction.getDate(),
                transaction.getCategory().getId(),
                transaction.getCategory().getName()
        );
    }
}
