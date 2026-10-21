package com.pizzaria.api.dto_response;

import com.pizzaria.api.entities.Endereco;

public record EnderecoResponseDTO(

        Long idEndereco,
        String rua,
        String numero,
        String bairro,
        String cidade,
        String estado,
        String cep,
        String complemento
) {
        public EnderecoResponseDTO(Endereco endereco) {

            this(
                    endereco.getId(),
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
