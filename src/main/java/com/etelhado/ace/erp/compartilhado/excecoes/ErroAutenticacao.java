package com.etelhado.ace.erp.compartilhado.excecoes;

public class ErroAutenticacao extends Exception {
    public ErroAutenticacao(String mensagem, Throwable th) {
        super(mensagem, th);
    }
}
