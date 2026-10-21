package com.pizzaria.api.dto_created.responseDtoFormat;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pizzaria.api.dto_response.PedidoDTO;

import java.util.List;

public record PedidoFormatLIstDTO(
        @JsonProperty("pedidos")
        List<PedidoDTO> pedidos
) {
}
//Formata a saida dos pedidos adicionando a chave "pedidos"