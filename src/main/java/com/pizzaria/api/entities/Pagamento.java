package com.pizzaria.api.entities;

import java.time.Instant;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pizzaria.api.enums.StatusPagamento;
import com.pizzaria.api.enums.TipoPagamento;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "pagamento")
public class Pagamento {
    @Id
    private Long id;
    @MapsId
    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;
    @NotNull
    private Integer tipoPagamento;
    @NotNull
    private Integer statusPagamento;

    private Instant dataHora;

    private Instant dataAtualizacao;

    public Pagamento() {
    }

    public Pagamento(Pedido pedido, TipoPagamento tipoPagamento, StatusPagamento statusPagamento, Instant dataHora) {
        this.pedido = pedido;
        this.tipoPagamento = (tipoPagamento != null) ? tipoPagamento.getCode() : null;
        this.statusPagamento = (statusPagamento != null) ? statusPagamento.getCode() : null;
        this.dataHora = dataHora;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Instant getDataHora() {
        return dataHora;
    }

    public void setDataHora(Instant dataHora) {
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

    public Instant getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(Instant dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }

    @PrePersist
    @PreUpdate
    public void atualizarDataPagamento(){
        this.dataAtualizacao = Instant.now();
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pagamento pagamento = (Pagamento) o;
        return Objects.equals(id, pagamento.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
