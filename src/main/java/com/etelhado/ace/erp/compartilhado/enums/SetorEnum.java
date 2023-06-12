package com.etelhado.ace.erp.compartilhado.enums;

public enum SetorEnum {
    ADMINISTRACAO("Administração"),
    GERENCIA("Gerencia"),
    FINANCEIRO("Financeiro"),
    FATURAMENTO("Faturamento"),
    VENDAS("Vendas"),
    RECEBIMENTO("Recebimento"),
    PRODUTO("Produto");

    private String displayName;

    private SetorEnum(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getName() {
        return this.name();
    }

}
