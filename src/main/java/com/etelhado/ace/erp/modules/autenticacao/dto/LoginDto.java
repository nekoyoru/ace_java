package com.etelhado.ace.erp.modules.autenticacao.dto;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDto {
    @NotBlank(message = "Informe o usuário")
    private String usuario;
    @NotBlank(message = "Informe a senha")
    @Length(min = 4, message = "Senha deve conter minimo 4 caracteres")
    private String senha;
}
