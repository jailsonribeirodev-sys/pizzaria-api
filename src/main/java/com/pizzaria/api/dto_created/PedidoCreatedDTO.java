package com.pizzaria.api.dto_created;

import com.pizzaria.api.enums.TipoPagamento;

import java.util.List;

public record PedidoCreatedDTO(
        Long idCliente,
        Long idEndereco,
        TipoPagamento tipoPagamento,

        List<ItemPedidoCreatedDTO> itensPedido
) {
}
