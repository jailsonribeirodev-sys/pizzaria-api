package com.pizzaria.api.dto_response;

import com.pizzaria.api.entities.Categoria;
import jakarta.validation.constraints.NotBlank;

public record CategoriaDTO(
        @NotBlank(message = "Nome é obrigatório")
        String nome,
        String descricao
) {
    public CategoriaDTO(Categoria categoria) {
        this(
                categoria.getNome(),
                categoria.getDescricao()
        );
    }
}
