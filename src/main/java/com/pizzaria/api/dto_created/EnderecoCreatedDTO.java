package com.pizzaria.api.dto_created;

import java.util.Set;

public record EnderecoCreatedDTO(

        Long clienteId,
        Set<EnderecoDTO> enderecos
) {
}
