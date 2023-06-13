package com.etelhado.ace.erp.modules.usuario.dto;

import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.etelhado.ace.erp.compartilhado.enums.SetorEnum;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UsuarioDto {
    private Long id;
    @NotBlank(message = "Informe o login")
    private String login;
    @NotBlank(message = "Informe a Senha")
    @Length(min = 6, max = 32, message = "Senha deve conter entre 06 e 32 caractéres")
    private String senha;
    private List<SetorEnum> setores;
    private Boolean ativo;
}
