package com.pizzaria.api.entities;

import java.math.BigDecimal;

public class ItemPedido {
    private Integer quantidade;
    private BigDecimal preco;
    private String observacao;

    public ItemPedido() {
    }

    public ItemPedido(Integer quantidade, BigDecimal preco, String observacao) {
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
