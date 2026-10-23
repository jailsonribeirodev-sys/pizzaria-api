package com.pizzaria.api.dto_response;

import com.pizzaria.api.entities.Cliente;

import java.util.Set;
import java.util.stream.Collectors;

public record ClientResponseDTO(
        Long idCliente,
        String nome,
        String cpfOuCnpj,
        String email,
        String telefone,
        Set<EnderecoResponseDTO> endereco
) {
    public ClientResponseDTO(Cliente c) {
        this(c.getId(),
                c.getNome(),
                c.getCpfOuCnpj(),
                c.getEmail(),
                c.getTelefone(),
                c.getEnderecos().stream().map(EnderecoResponseDTO::new).collect(Collectors.toSet())
        );
    }



}
