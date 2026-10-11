package com.pizzaria.api.dto;

import com.pizzaria.api.entities.Cliente;

public record ClienteDTO(
        String nome,
        String cpfOuCnpj,
        String email,
        String telefone
) {
    public ClienteDTO(Cliente c) {
        this(
                c.getNome(),
                c.getCpfOuCnpj(),
                c.getEmail(),
                c.getTelefone()
        );
    }
}
