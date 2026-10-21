package com.pizzaria.api.dto_created;

import com.pizzaria.api.entities.Endereco;

public record EnderecoDTO(

        String rua,
        String numero,
        String bairro,
        String cidade,
        String estado,
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
