package com.pizzaria.api.entities;

import java.math.BigDecimal;

import com.pizzaria.api.entities.pk.ItemPedidoPk;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;

public class ItemPedido {

    private Integer quantidade;
    private BigDecimal preco;
    private String observacao;

    @EmbeddedId
    private ItemPedidoPk id = new ItemPedidoPk();

    @ManyToOne
    @MapsId("produtoId")
    @JoinColumn(name = "produto_id")
    private Produto produto;
    @ManyToOne
    @MapsId("pedidoId")
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

    public ItemPedido() {
    }

    public ItemPedido(Produto produto, Pedido pedido, Integer quantidade, BigDecimal preco, String observacao) {
        this.quantidade = quantidade;
        this.preco = preco;
        this.observacao = observacao;

    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

}
