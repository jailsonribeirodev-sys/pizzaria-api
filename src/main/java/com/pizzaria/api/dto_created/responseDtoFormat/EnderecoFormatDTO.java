package com.pizzaria.api.dto_created.responseDtoFormat;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pizzaria.api.dto_response.EnderecoResponseDTO;

import java.util.Set;

public record EnderecoFormatDTO(
        @JsonProperty("enderecos")
        Set<EnderecoResponseDTO> enderecos
) {

}
