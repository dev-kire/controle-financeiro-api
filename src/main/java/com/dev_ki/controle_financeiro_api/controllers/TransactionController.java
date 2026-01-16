package com.dev_ki.controle_financeiro_api.controllers;

import com.dev_ki.controle_financeiro_api.domain.transaction.TransactionService;
import com.dev_ki.controle_financeiro_api.dto.TransactionCreateDTO;
import com.dev_ki.controle_financeiro_api.dto.TransactionResponseDTO;
import com.dev_ki.controle_financeiro_api.dto.TransactionUpdateDTO;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TransactionResponseDTO create(
            @RequestBody @Valid TransactionCreateDTO dto
    ) {
        return transactionService.create(dto);
    }

    @GetMapping
    public List<TransactionResponseDTO> list() {
        return transactionService.list();
    }

    @GetMapping("/{id}")
    public TransactionResponseDTO findById(@PathVariable UUID id) {
        return transactionService.findById(id);
    }

    @PutMapping("/{id}")
    public TransactionResponseDTO update(
            @PathVariable UUID id,
            @RequestBody @Valid TransactionUpdateDTO dto
    ) {
        return transactionService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        transactionService.delete(id);
    }
}
