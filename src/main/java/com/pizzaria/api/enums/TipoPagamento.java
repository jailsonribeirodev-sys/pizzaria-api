package com.pizzaria.api.enums;

public enum TipoPagamento {
    PIX(1),
    CARTAO_CREDITO(2),
    CARTAO_DEBITO(3),
    DINHEIRO(4),
    VALE_REFEICAO(5);

    private Integer code;

    TipoPagamento(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return this.code;
    }

    public static TipoPagamento valueOf(Integer code) {
        for (TipoPagamento value : TipoPagamento.values()) {
            if (value.getCode().equals(code)) {
                return value;
            }
        }
        throw new IllegalArgumentException("Código inválido: " + code);
    }
}
