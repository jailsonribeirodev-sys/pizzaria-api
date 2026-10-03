package com.pizzaria.api.enums;

public enum StatusPedido {
    RECEBIDO(1),
    EM_PREPARO(2),
    NO_FORNO(3),
    EM_ROTA(4),
    ENTREGUE(5),
    CANCELADO(6);

    private Integer code;

    StatusPedido(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public static StatusPedido valueOf(Integer code) {
        for (StatusPedido value : StatusPedido.values()) {
            if (value.getCode() == code) {
                return value;
            }
        }
        throw new IllegalArgumentException("Código inválido: " + code);
    }
}
