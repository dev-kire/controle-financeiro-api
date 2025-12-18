package com.dev_ki.controle_financeiro_api.domain.user;

import com.dev_ki.controle_financeiro_api.dto.LoginDTO;
import com.dev_ki.controle_financeiro_api.dto.LoginResponseDTO;
import com.dev_ki.controle_financeiro_api.dto.UserCreateDTO;
import com.dev_ki.controle_financeiro_api.dto.UserMeResponseDTO;
import org.antlr.v4.runtime.Token;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.dev_ki.controle_financeiro_api.infra.security.TokenService;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service
public class UserService  implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder, TokenService tokenService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
    }

    public void criar(UserCreateDTO dto) {
        User user = new User();

        user.setNome(dto.getNome());
        user.setEmail(dto.getEmail());
        user.setSenha(passwordEncoder.encode(dto.getSenha()));
        userRepository.save(user);
    }

    public LoginResponseDTO login(LoginDTO dto){
        User user = userRepository.findByEmail((dto.getEmail()))
                .orElseThrow(() -> new RuntimeException(("Usuário não encontrado")));

        if (!passwordEncoder.matches(dto.getSenha(), user.getSenha())){
            throw new RuntimeException("Senha incorreta");
        }

        String token = tokenService.gerarToken(user);

        Instant expiresAt = Instant.now().plus(2, ChronoUnit.HOURS);

        return new LoginResponseDTO(token, expiresAt);
    }
    public UserMeResponseDTO me(){
        Authentication authentication = SecurityContextHolder.getContext()
                .getAuthentication();

        String email = authentication.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        return new UserMeResponseDTO(
                user.getId(),
                user.getNome(),
                user.getEmail()
        );
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getEmail())
                .password(user.getSenha())
                .authorities("USER")
                .build();
    }
}





