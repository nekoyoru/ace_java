package com.etelhado.ace.erp.modules.autenticacao.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TokenResponseDto {
    private String token;
    private String refreshToken;
}
