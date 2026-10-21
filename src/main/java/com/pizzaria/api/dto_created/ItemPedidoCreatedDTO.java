package com.pizzaria.api.dto_created;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record ItemPedidoCreatedDTO(

      @NotNull  Long idProduto,

        Integer quantidade,
        String observacao
) {
        public ItemPedidoCreatedDTO{
               if(observacao == null||observacao.isBlank()){
                       observacao = "Sem observações";
               }
        }
}
