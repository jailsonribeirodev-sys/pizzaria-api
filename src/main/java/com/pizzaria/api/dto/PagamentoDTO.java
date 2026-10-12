package com.pizzaria.api.dto;

import com.pizzaria.api.entities.Pagamento;
import com.pizzaria.api.enums.StatusPagamento;
import com.pizzaria.api.enums.TipoPagamento;

import java.time.Instant;

public record PagamentoDTO(
        TipoPagamento tipoPagamento,
        StatusPagamento statusPagamento,
        Instant dataHora
) {
    public PagamentoDTO(Pagamento pagamento) {
        this(
                pagamento.getTipoPagamento(),
                pagamento.getStatusPagamento(),
                pagamento.getDataHora()
        );
    }
}
