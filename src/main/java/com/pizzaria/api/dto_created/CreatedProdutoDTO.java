package com.pizzaria.api.dto_created;

import com.pizzaria.api.dto_response.ProdutoDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.Set;

public record CreatedProdutoDTO(
        @NotNull(message = "Id da categoria é obrigatório")
        @Positive(message = "Apenas numero inteiro e positivo")
        Long categoriaId,
        @NotEmpty(message = "A lista de produtos não pode estar vazia")
        Set<@Valid ProdutoDTO> produtos
) {
}
