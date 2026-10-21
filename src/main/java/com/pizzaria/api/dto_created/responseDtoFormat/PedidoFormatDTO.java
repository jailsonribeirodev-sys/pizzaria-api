package com.pizzaria.api.dto_created.responseDtoFormat;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pizzaria.api.dto_response.PedidoDTO;

public record PedidoFormatDTO(
        @JsonProperty("pedido")
        PedidoDTO pedidos
) {
}
//Formata a saida dos pedidos adicionando a chave "pedidos"