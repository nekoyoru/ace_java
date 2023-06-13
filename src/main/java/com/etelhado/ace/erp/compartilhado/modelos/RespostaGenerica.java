package com.etelhado.ace.erp.compartilhado.modelos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RespostaGenerica {
    private Integer status;
    private String mensagem;
}
