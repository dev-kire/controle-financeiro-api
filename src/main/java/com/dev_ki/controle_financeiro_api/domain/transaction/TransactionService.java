package com.dev_ki.controle_financeiro_api.domain.transaction;


import com.dev_ki.controle_financeiro_api.domain.category.Category;
import com.dev_ki.controle_financeiro_api.domain.category.CategoryRepository;
import com.dev_ki.controle_financeiro_api.dto.TransactionCreateDTO;
import com.dev_ki.controle_financeiro_api.dto.TransactionResponseDTO;
import com.dev_ki.controle_financeiro_api.dto.TransactionUpdateDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final CategoryRepository categoryRepository;
    private Category category;

    public TransactionService(
            TransactionRepository transactionRepository,
            CategoryRepository categoryRepository
    ) {
        this.transactionRepository = transactionRepository;
        this.categoryRepository = categoryRepository;
    }

    public TransactionResponseDTO create(TransactionCreateDTO dto) {
        Category category = categoryRepository.findById(dto.getCategoryId())
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

    public List<TransactionResponseDTO> list(){
        return transactionRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public TransactionResponseDTO findById(UUID id) {
        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transação não encontrada"));

        return toResponse(transaction);
    }

    public TransactionResponseDTO update(UUID id, TransactionUpdateDTO dto) {
        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transação não encontrada"));

        transaction.setDescription(dto.getDescription());
        transaction.setAmount(dto.getAmount());
        transaction.setType(TransactionType.valueOf(dto.getType()));
        transaction.setCategory(category);
        transaction.setDate(dto.getDate());

        Transaction updated = transactionRepository.save(transaction);
        return toResponse(updated);
    }

    public void delete(UUID id) {
        if (!transactionRepository.existsById(id)) {
            throw new RuntimeException("Transação não encontrada");
        }
        transactionRepository.deleteById(id);
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
