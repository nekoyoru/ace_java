package com.etelhado.ace.erp.modules.usuario.dto;

import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.etelhado.ace.erp.compartilhado.enums.SetorEnum;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioFormCadDto {
    @NotBlank(message = "Informe o login")
    private String login;
    @NotBlank(message = "Informe uma senha")
    @Length(min = 6, message = "Senha deve conter no minimo 6 digitos")
    private String senha;
    @Length(min = 6, message = "Senha deve conter no minimo 6 digitos")
    private String confirmarSenha;
    @Size(min = 1, message = "Selecione ao menos um setor")
    private List<SetorEnum> setores;
}
