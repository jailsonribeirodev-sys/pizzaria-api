package com.pizzaria.api.dto_response;

import com.pizzaria.api.entities.ItemPedido;

import java.math.BigDecimal;

public record ItemPedidoDTO(
        Long idProduto,
        String nome,
        Integer quantidade,
        BigDecimal preco,
        String observacao,
        BigDecimal subtotal
) {
    public ItemPedidoDTO(ItemPedido itemPedido) {
        this(itemPedido.getProduto().getId(),
                itemPedido.getProduto().getNome(),
                itemPedido.getQuantidade(),
                itemPedido.getPreco(),
                itemPedido.getObservacao(),
                itemPedido.getSubtotal()
        );
    }
}
