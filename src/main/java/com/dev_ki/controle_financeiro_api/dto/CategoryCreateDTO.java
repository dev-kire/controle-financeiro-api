package com.dev_ki.controle_financeiro_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CategoryCreateDTO {

    @NotBlank
    private String name;

    @NotNull
    private String type;

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }
}
