package com.pizzaria.api.dto_response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pizzaria.api.entities.Pagamento;
import com.pizzaria.api.enums.StatusPagamento;
import com.pizzaria.api.enums.TipoPagamento;

import java.time.Instant;

public record PagamentoDTO(

        TipoPagamento tipoPagamento,
        StatusPagamento statusPagamento,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "America/Fortaleza")
        Instant dataHora,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "America/Fortaleza")
        Instant dataAtualizacao
) {
    public PagamentoDTO(Pagamento pagamento) {
        this(
                pagamento.getTipoPagamento(),
                pagamento.getStatusPagamento(),
                pagamento.getDataHora(),
                pagamento.getDataAtualizacao()

        );
    }
}
