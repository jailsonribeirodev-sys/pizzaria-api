package com.pizzaria.api.dto;

import com.pizzaria.api.entities.Produto;

import java.math.BigDecimal;
import java.time.Instant;

public record ProdutoDTO(
        String nome,
        BigDecimal preco,
        Instant dataCadastro
) {
    public ProdutoDTO(Produto produto) {
        this(produto.getNome(),
                produto.getPreco(),
                produto.getDataCadastro());
    }
}
