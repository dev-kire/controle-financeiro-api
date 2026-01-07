package com.dev_ki.controle_financeiro_api.controllers;

import com.dev_ki.controle_financeiro_api.domain.category.Category;
import com.dev_ki.controle_financeiro_api.domain.category.CategoryService;
import com.dev_ki.controle_financeiro_api.dto.CategoryCreateDTO;
import com.dev_ki.controle_financeiro_api.dto.CategoryResponseDTO;
import com.dev_ki.controle_financeiro_api.dto.CategoryUpdateDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryResponseDTO create(@RequestBody @Valid CategoryCreateDTO dto) {
        return categoryService.create(dto);
    }

    @GetMapping
    public List<CategoryResponseDTO> list() {
        return categoryService.list();
    }

    @GetMapping("/{id}")
    public CategoryResponseDTO findById(@PathVariable UUID id) {
        return categoryService.findById(id);
    }

    @PutMapping("/{id}")
    public CategoryResponseDTO update(
            @PathVariable UUID id,
            @RequestBody @Valid CategoryUpdateDTO dto
    ) {
        return categoryService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id){
        categoryService.delete(id);
    }
 }
