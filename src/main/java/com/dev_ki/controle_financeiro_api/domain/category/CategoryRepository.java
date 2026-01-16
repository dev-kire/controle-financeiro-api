package com.dev_ki.controle_financeiro_api.domain.category;

import com.dev_ki.controle_financeiro_api.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, UUID> {

    List<Category> findByUser(User user);
}
