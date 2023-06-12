package com.etelhado.ace.erp.modules.autenticacao.services;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.etelhado.ace.erp.compartilhado.entidades.Usuario;
import com.etelhado.ace.erp.mappers.LoginMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoginService implements UserDetailsService {

    private final LoginMapper loginRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> usuOptional = loginRepository.buscarUsuarioPorLogin(username);
        if (usuOptional.isPresent()) {
            Usuario u = usuOptional.get();
            Collection<SimpleGrantedAuthority> roles = u.getSetores().stream().map(t -> {
                return new SimpleGrantedAuthority("ROLE_" + t.getName());
            }).collect(Collectors.toUnmodifiableList());
            User user = new User(u.getLogin(), u.getSenha(), u.getAtivo(), true, true, true, roles);
            return user;
        }
        throw new UsernameNotFoundException("Dados de login inválidos");
    }

    @CacheEvict(key = "#login", value = "usuariosCache")
    public void logOut(String login) {

    }
}
