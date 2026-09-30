package com.pizzaria.api.entities;

import java.time.Instant;

import com.pizzaria.api.enums.TipoPagamento;

import lombok.Data;

@Data
public class Pagamento {
    private Long id;
    private TipoPagamento tipoPagamento;
    private Instant dataHora;

    public Pagamento() {
    }

    public Pagamento(Long id, TipoPagamento tipoPagamento, Instant dataHora) {
        this.id = id;
        this.tipoPagamento = tipoPagamento;
        this.dataHora = dataHora;
    }
}
