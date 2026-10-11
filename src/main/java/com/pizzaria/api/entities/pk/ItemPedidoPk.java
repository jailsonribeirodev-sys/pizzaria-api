package com.pizzaria.api.entities.pk;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Embeddable;

@Embeddable
public class ItemPedidoPk implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private Long produtoId;
    private Long pedidoId;

    public ItemPedidoPk() {
    }

    public ItemPedidoPk(Long produtoId, Long pedidoId) {
        this.produtoId = produtoId;
        this.pedidoId = pedidoId;
    }

    public Long getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Long produtoId) {
        this.produtoId = produtoId;
    }

    public Long getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(Long pedidoId) {
        this.pedidoId = pedidoId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemPedidoPk that = (ItemPedidoPk) o;
        return Objects.equals(produtoId, that.produtoId) && Objects.equals(pedidoId, that.pedidoId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(produtoId, pedidoId);
    }
}
