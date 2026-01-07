package com.dev_ki.controle_financeiro_api.dto;

import java.util.UUID;

public class CategoryResponseDTO {

    private UUID id;
    private String name;
    private String type;

    public CategoryResponseDTO(UUID id, String name, String type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }
}
