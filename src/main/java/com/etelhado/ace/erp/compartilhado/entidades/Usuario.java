package com.etelhado.ace.erp.compartilhado.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import com.etelhado.ace.erp.compartilhado.enums.SetorEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Usuario implements Serializable {
    private Long id;
    private String login;
    private String senha;
    private List<SetorEnum> setores;
    private Boolean ativo;
    private Date dataCadastro;
    private Date dataAtualizacao;
    private Date dataExclusao;
}
