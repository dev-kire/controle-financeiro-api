package com.dev_ki.controle_financeiro_api.controllers;

import com.dev_ki.controle_financeiro_api.domain.user.UserService;
import com.dev_ki.controle_financeiro_api.dto.LoginDTO;
import com.dev_ki.controle_financeiro_api.dto.LoginResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid LoginDTO dto){
        LoginResponseDTO response = userService.login(dto);
        return ResponseEntity.ok(response);
    }
}
