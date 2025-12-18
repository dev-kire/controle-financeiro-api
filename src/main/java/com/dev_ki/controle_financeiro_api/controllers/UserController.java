package com.dev_ki.controle_financeiro_api.controllers;

import com.dev_ki.controle_financeiro_api.domain.user.UserService;
import com.dev_ki.controle_financeiro_api.dto.LoginDTO;
import com.dev_ki.controle_financeiro_api.dto.LoginResponseDTO;
import com.dev_ki.controle_financeiro_api.dto.UserCreateDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void criar(@RequestBody @Valid UserCreateDTO dto){
        userService.criar(dto);
    }

    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody LoginDTO dto){
        return userService.login(dto);
    }

    @GetMapping("/me")
    public String me(){
        return "Acesso Autorizado!";
    }
}
