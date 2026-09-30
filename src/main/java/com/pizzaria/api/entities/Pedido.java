package com.pizzaria.api.entities;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Set;

import com.pizzaria.api.enums.StatusPedido;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class Pedido {
    private Long id;
    private Set<ItemPedido> itens;
    @ManyToOne
    @JoinColumn(name = "endereco_id")
    private Endereco endereco;
    private BigDecimal total;
    private StatusPedido status;
    private Instant dataHora;
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    public Pedido() {
    }

    public Pedido(Long id, Cliente cliente, Endereco endereco,
            BigDecimal total, StatusPedido status, Instant dataHora) {
        this.id = id;
        this.cliente = cliente;
        this.endereco = endereco;
        this.total = total;
        this.status = status;
        this.dataHora = dataHora;
    }
}
