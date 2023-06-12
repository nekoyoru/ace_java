package com.etelhado.ace.erp.compartilhado.modelos;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RespostaErroPadraoDto {
    private Integer httpStatus;
    private String mensagem;
    private String classe;
    private List<ErroValidacaoDto> validacoes;
}
