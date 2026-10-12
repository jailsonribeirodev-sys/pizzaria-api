package com.pizzaria.api.dto_created;

import java.util.List;

public record PedidoCreatedDTO(
Long clienteId,
List<ItemPedidoCreatedDTO> itens
) {
}
