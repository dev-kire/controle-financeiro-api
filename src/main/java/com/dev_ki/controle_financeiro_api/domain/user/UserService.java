package com.dev_ki.controle_financeiro_api.domain.user;

import com.dev_ki.controle_financeiro_api.dto.LoginDTO;
import com.dev_ki.controle_financeiro_api.dto.UserCreateDTO;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService  implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void criar(UserCreateDTO dto) {
        User user = new User();

        user.setNome(dto.getNome());
        user.setEmail(dto.getEmail());

        String senhaCriptografada = passwordEncoder.encode(dto.getSenha());
        user.setSenha(senhaCriptografada);

        userRepository.save(user);
    }

    public String login(LoginDTO dto){
        User user = userRepository.findByEmail(dto.getEmail()).
                orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        boolean senhaCorreta = passwordEncoder.matches(dto.getSenha(), user.getSenha());
        if (!senhaCorreta){
            throw new RuntimeException("Senha incorreta");
        }
        return "LOGIN_OK";
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







