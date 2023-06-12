package com.etelhado.ace.erp.config;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.etelhado.ace.erp.compartilhado.modelos.RespostaErroPadraoDto;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AutenticacaoEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException authException) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        RespostaErroPadraoDto respostaErroPadraoDto = new RespostaErroPadraoDto();
        respostaErroPadraoDto.setClasse(authException.getClass().getSimpleName());
        respostaErroPadraoDto.setHttpStatus(HttpServletResponse.SC_UNAUTHORIZED);
        respostaErroPadraoDto.setMensagem("Autenticação obrigatória");
        ObjectMapper objectMapper = new ObjectMapper();
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        PrintWriter w = response.getWriter();
        w.write(StringUtils.toEncodedString(
                objectMapper.writeValueAsString(respostaErroPadraoDto).getBytes(StandardCharsets.UTF_8),
                StandardCharsets.UTF_8));
        w.flush();
    }
}
