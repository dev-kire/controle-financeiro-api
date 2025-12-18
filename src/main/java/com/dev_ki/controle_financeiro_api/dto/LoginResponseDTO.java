package com.dev_ki.controle_financeiro_api.dto;

import java.time.Instant;

public class LoginResponseDTO {

    private String type;
    private String token;
    private Instant expiresAt;

    public LoginResponseDTO(String token, Instant expiresAt){
        this.type = "Bearer";
        this.token = token;
        this.expiresAt = expiresAt;
    }

    public String getType(){
        return type;
    }

    public String getToken(){
        return token;
    }

    public Instant getExpiresAt(){
        return expiresAt;
    }
}
