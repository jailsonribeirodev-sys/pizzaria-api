package com.pizzaria.api.dto_created;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import com.pizzaria.api.entities.Endereco;

public record EnderecoDTO(
        @NotBlank(message = "Rua é obrigatória")
        String rua,
        @NotBlank(message = "Número é obrigatório")
        String numero,
        @NotBlank(message = "Bairro é obrigatório")
        String bairro,
        @NotBlank(message = "Cidade é obrigatória")
        String cidade,
        @NotBlank(message = "Estado é obrigatório")
        String estado,
        @NotBlank(message = "CEP é obrigatório")
        @Pattern(regexp = "\\d{5}-?\\d{3}", message = "CEP inválido")
        String cep,
        String complemento
) {
    public EnderecoDTO(Endereco endereco) {
        this(
                endereco.getRua(),
                endereco.getNumero(),
                endereco.getBairro(),
                endereco.getCidade(),
                endereco.getEstado(),
                endereco.getCep(),
                endereco.getComplemento()
        );
    }
}
