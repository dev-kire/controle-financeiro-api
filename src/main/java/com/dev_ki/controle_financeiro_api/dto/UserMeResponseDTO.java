package com.dev_ki.controle_financeiro_api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public class UserMeResponseDTO {

    private String email;

    public UserMeResponseDTO(UUID id, String nome, String email){
        this.email = email;
    }

    public String getEmail(){
        return email;
    }

}
