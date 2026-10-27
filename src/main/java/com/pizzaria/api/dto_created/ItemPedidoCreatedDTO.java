package com.pizzaria.api.dto_created;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ItemPedidoCreatedDTO(

        @NotNull(message = "Id do produto é obrigatório")
        @Positive(message = "Id do produto deve ser positivo")
        Long idProduto,

        @NotNull(message = "Quantidade é obrigatória")
        @Positive(message = "Quantidade deve ser maior que zero")
        Integer quantidade,
        String observacao
) {
        public ItemPedidoCreatedDTO{
               if(observacao == null||observacao.isBlank()){
                       observacao = "Sem observações";
               }
        }
}
