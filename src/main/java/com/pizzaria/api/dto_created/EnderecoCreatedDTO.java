package com.pizzaria.api.dto_created;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.Set;

public record EnderecoCreatedDTO(
        @NotNull(message = "Id do cliente é obrigatório")
        @Positive(message = "Apenas numero iteiro e positivo")
        Long clienteId,
        @NotEmpty(message = "A lista de endereços não pode estar vazia")
        Set<@Valid EnderecoDTO> enderecos
) {
}
