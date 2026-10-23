package com.pizzaria.api.dto_created;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.Set;

public record EnderecoCreatedDTO(
@Positive(message = "Apenas numero iteiro e positivo")
        Long clienteId,
        Set<EnderecoDTO> enderecos
) {
}
