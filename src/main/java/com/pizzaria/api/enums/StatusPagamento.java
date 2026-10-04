package com.pizzaria.api.enums;

public enum StatusPagamento {
    PENDENTE(1),
    PAGO(2),
    CANCELADO(3);

    private Integer code;

    StatusPagamento(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public static StatusPagamento valueOf(Integer code) {
        for (StatusPagamento value : StatusPagamento.values()) {
            if (value.getCode() == code) {
                return value;
            }
        }
        throw new IllegalArgumentException("Código inválido: " + code);
    }
}
