package com.etelhado.ace.erp.modules.usuario.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UsuarioListaDto implements Serializable {
    private Long id;
    private String login;
    private Boolean ativo;
    private Date dataCadastro;
    private Date dataAtualizacao;
    private Date dataExclusao;
}