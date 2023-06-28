package com.etelhado.ace.erp.compartilhado.excecoes;

import java.util.List;

import com.etelhado.ace.erp.compartilhado.modelos.ErroValidacaoDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValidationViolationException extends Exception {
    private String message;
    private Throwable th;
    private List<ErroValidacaoDto> validacoes;

    public ValidationViolationException(String message, Throwable th, List<ErroValidacaoDto> validacoes) {
        this.message = message;
        this.th = th;
        this.validacoes = validacoes;
    }
}
