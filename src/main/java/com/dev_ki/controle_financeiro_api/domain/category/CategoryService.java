package com.dev_ki.controle_financeiro_api.domain.category;

import com.dev_ki.controle_financeiro_api.domain.user.User;
import com.dev_ki.controle_financeiro_api.domain.user.UserRepository;
import com.dev_ki.controle_financeiro_api.dto.CategoryCreateDTO;
import com.dev_ki.controle_financeiro_api.dto.CategoryResponseDTO;
import com.dev_ki.controle_financeiro_api.dto.CategoryUpdateDTO;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CategoryService {

    private User getAuhenticatedUser() {
        String email = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }

    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    public CategoryService(CategoryRepository categoryRepository,
    UserRepository userRepository) {
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
    }

    public CategoryResponseDTO create(CategoryCreateDTO dto) {
        User user = getAuhenticatedUser();

        Category category = new Category();
        category.setName(dto.getName());
        category.setType(dto.getType());
        category.setUser(user);

        Category saved = categoryRepository.save(category);
        return toResponse(saved);
    }

    public List<CategoryResponseDTO> list() {
        User user = getAuhenticatedUser();

        return categoryRepository.findByUser(user)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public CategoryResponseDTO findById(UUID id) {
        User user = getAuhenticatedUser();

        Category category = categoryRepository.findById(id)
                .filter(c -> c.getUser().getId().equals(user.getId()))
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

        return toResponse(category);
    }

    public CategoryResponseDTO update(UUID id, CategoryUpdateDTO dto) {
        User user = getAuhenticatedUser();

        Category category = categoryRepository.findById(id)
                .filter(c -> c.getUser().getId().equals(user.getId()))
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

        category.setName(dto.getName());
        category.setType(dto.getType());

        Category updated = categoryRepository.save(category);
        return toResponse(updated);
    }

    public void delete(UUID id) {
        User user = getAuhenticatedUser();

        Category category = categoryRepository.findById(id)
                .filter(c -> c.getUser().getId().equals(user.getId()))
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

        categoryRepository.delete(category);
        }

    private CategoryResponseDTO toResponse(Category category) {
        return new CategoryResponseDTO(
                category.getId(),
                category.getName(),
                category.getType()
        );
    }
}
