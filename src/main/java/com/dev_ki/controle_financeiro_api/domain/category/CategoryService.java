package com.dev_ki.controle_financeiro_api.domain.category;

import com.dev_ki.controle_financeiro_api.dto.CategoryCreateDTO;
import com.dev_ki.controle_financeiro_api.dto.CategoryResponseDTO;
import com.dev_ki.controle_financeiro_api.dto.CategoryUpdateDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public CategoryResponseDTO create(CategoryCreateDTO dto) {
        Category category = new Category();
        category.setName(dto.getName());
        category.setType(dto.getType());

        Category saved = categoryRepository.save(category);
        return toResponse(saved);
    }

    public List<CategoryResponseDTO> list() {
        return categoryRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public CategoryResponseDTO findById(UUID id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

        return toResponse(category);
    }

    public CategoryResponseDTO update(UUID id, CategoryUpdateDTO dto) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

        category.setName(dto.getName());
        category.setType(dto.getType());

        Category updated = categoryRepository.save(category);
        return toResponse(updated);
    }

    public void delete(UUID id) {
        if (!categoryRepository.existsById(id)) {
            throw new RuntimeException("Categoria não encontrada");
        }
        categoryRepository.deleteById(id);
    }

    private CategoryResponseDTO toResponse(Category category) {
        return new CategoryResponseDTO(
                category.getId(),
                category.getName(),
                category.getType()
        );
    }
}
