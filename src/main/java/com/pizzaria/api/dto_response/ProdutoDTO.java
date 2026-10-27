package com.pizzaria.api.dto_response;

import com.pizzaria.api.entities.Produto;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.Instant;

public record ProdutoDTO(
        @NotBlank(message = "Nome é obrigatório")
        String nome,
        @NotNull(message = "Preço é obrigatório")
        @DecimalMin(value = "0.01", message = "O preço deve ser maior que zero")
        BigDecimal preco,
        Instant dataCadastro
) {
    public ProdutoDTO(Produto produto) {
        this(produto.getNome(),
                produto.getPreco(),
                produto.getDataCadastro());
    }
}
