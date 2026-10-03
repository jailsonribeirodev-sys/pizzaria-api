package com.pizzaria.api.enums;

public enum TipoPagamento {
    PIX(1),
    CARTAO_CREDITO(2),
    CARTAO_DEBITO(3),
    DINHEIRO(4),
    VALE_REFEICAO(5);

    private int code;

    TipoPagamento(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static TipoPagamento valueOf(int code) {
        for (TipoPagamento value : TipoPagamento.values()) {
            if (value.getCode() == code) {
                return value;
            }
        }
        throw new IllegalArgumentException("Código inválido: " + code);
    }
}
