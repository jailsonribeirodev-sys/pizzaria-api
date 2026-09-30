package com.pizzaria.api.entities.pk;

import com.pizzaria.api.entities.Pedido;
import com.pizzaria.api.entities.Produto;

public class ItemPedidoPk {
    private Produto produto;
    private Pedido pedido;

    public ItemPedidoPk() {

    }

    public ItemPedidoPk(Produto produto, Pedido pedido) {
        this.produto = produto;
        this.pedido = pedido;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

}
