package com.pizzaria.api.dto_response;

import com.pizzaria.api.entities.Categoria;

public record CategoriaDTO(
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
