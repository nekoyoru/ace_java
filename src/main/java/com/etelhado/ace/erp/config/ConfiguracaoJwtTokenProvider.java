package com.etelhado.ace.erp.config;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

import com.etelhado.ace.erp.modules.autenticacao.services.LoginService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class ConfiguracaoJwtTokenProvider {
    private static final long EXPIRATION_TIME = 24 * 60 * 60 * 1000; // 24 horas
    private static final SecretKey SECRET_KEY2 = Keys.secretKeyFor(SignatureAlgorithm.HS512);
    private final LoginService loginService;

    public String criarToken(UserDetails usuario) {
        Claims claims = Jwts.claims().setSubject(usuario.getUsername());
        claims.put("roles", usuario.getAuthorities());

        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SECRET_KEY2)
                .compact();
    }

    public Authentication pegarAutenticacao(String token) throws Exception {
        String username = pegarUsuarioDoToken(token);
        UserDetails user = loginService.loadUserByUsername(username);
        return new UsernamePasswordAuthenticationToken(user, user.getAuthorities());
    }

    public String pegarUsuarioDoToken(String token) {
        String nome = Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY2)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
        return nome;
    }

    public boolean validarToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(SECRET_KEY2)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String resolverToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}
