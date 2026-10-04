package com.pizzaria.api.entities;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pizzaria.api.enums.StatusPagamento;
import com.pizzaria.api.enums.TipoPagamento;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "pagamento")
public class Pagamento {
    @Id
    @EqualsAndHashCode.Include
    private Long id;
    @MapsId
    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;
    private Integer tipoPagamento;
    private Integer statusPagamento;
    private Instant dataHora;

    public Pagamento(Pedido pedido, TipoPagamento tipoPagamento, StatusPagamento statusPagamento, Instant dataHora) {
        this.pedido = pedido;
        this.tipoPagamento = (tipoPagamento != null) ? tipoPagamento.getCode() : null;
        this.statusPagamento = (statusPagamento != null) ? statusPagamento.getCode() : null;
        this.dataHora = dataHora;
    }

    public TipoPagamento getTipoPagamento() {
        return this.tipoPagamento != null ? TipoPagamento.valueOf(tipoPagamento) : null;
    }

    public void setTipoPagamento(TipoPagamento tipoPagamento) {
        this.tipoPagamento = (tipoPagamento != null) ? tipoPagamento.getCode() : null;
    }

    public StatusPagamento getStatusPagamento() {
        return this.statusPagamento != null ? StatusPagamento.valueOf(statusPagamento) : null;
    }

    public void setStatusPagamento(StatusPagamento statusPagamento) {
        this.statusPagamento = (statusPagamento != null) ? statusPagamento.getCode() : null;
    }

}
