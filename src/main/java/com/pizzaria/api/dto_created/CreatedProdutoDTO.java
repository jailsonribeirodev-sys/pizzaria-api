package com.pizzaria.api.dto_created;

import com.pizzaria.api.dto_response.ProdutoDTO;
import jakarta.validation.constraints.Positive;

import java.util.Set;

public record CreatedProdutoDTO(
        @Positive(message = "Apenas numero inteiro e positivo")
        Long categoriaId,
        Set<ProdutoDTO> produtos
) {
}
