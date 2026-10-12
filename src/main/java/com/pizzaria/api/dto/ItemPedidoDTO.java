package com.pizzaria.api.dto;

import com.pizzaria.api.entities.ItemPedido;
import com.pizzaria.api.entities.pk.ItemPedidoPk;

import java.math.BigDecimal;

public record ItemPedidoDTO(
        Long id,
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
