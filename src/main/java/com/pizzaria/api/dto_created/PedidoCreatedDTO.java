package com.pizzaria.api.dto_created;

import com.pizzaria.api.enums.TipoPagamento;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.List;

public record PedidoCreatedDTO(
        @NotNull(message = "Id do cliente é obrigatório")
        @Positive(message = "Id do cliente deve ser positivo")
        Long idCliente,

        @NotNull(message = "Id do endereço é obrigatório")
        @Positive(message = "Id do endereço deve ser positivo")
        Long idEndereco,

        @NotNull(message = "Tipo de pagamento é obrigatório")
        TipoPagamento tipoPagamento,

        @NotEmpty(message = "O pedido deve ter pelo menos um item")
        List<@Valid ItemPedidoCreatedDTO> itensPedido
) {
}
