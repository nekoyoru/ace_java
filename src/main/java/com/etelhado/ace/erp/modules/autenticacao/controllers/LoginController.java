package com.etelhado.ace.erp.modules.autenticacao.controllers;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.etelhado.ace.erp.config.ConfiguracaoJwtTokenProvider;
import com.etelhado.ace.erp.modules.autenticacao.dto.LoginDto;
import com.etelhado.ace.erp.modules.autenticacao.dto.TokenResponseDto;
import com.etelhado.ace.erp.modules.autenticacao.services.LoginService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final AuthenticationManager authenticationManager;
    private final LoginService loginService;
    private final ConfiguracaoJwtTokenProvider tokenProvider;

    @DeleteMapping(path = "/logoff", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, String>> logout() {
        loginService.logOut(SecurityContextHolder.getContext().getAuthentication().getName());
        Map<String, String> map = new LinkedHashMap<>();
        map.put("mensagem", "ok");
        return ResponseEntity.ok(map);
    }

    @PostMapping(path = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TokenResponseDto> realizarLogin(@RequestBody @Valid LoginDto dto) {
        var usuario = loginService.loadUserByUsername(dto.getUsuario());
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(usuario.getUsername(), dto.getSenha()));
        String token = tokenProvider.criarToken(usuario);
        TokenResponseDto tokenResponseDto = new TokenResponseDto();
        tokenResponseDto.setToken(token);
        return ResponseEntity.ok(tokenResponseDto);
    }

    @GetMapping(path = "seguro")
    @Secured("ROLE_ADMINISTRACAO")
    public ResponseEntity<String> seguro() {
        return ResponseEntity.ok("ok");
    }
}
