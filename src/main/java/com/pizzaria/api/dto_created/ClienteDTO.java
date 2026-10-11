package com.pizzaria.api.dto_created;

import com.pizzaria.api.entities.Cliente;

public record ClienteDTO(
        Long id,
        String nome,
        String telefone) {
    public ClienteDTO(Cliente cliente) {
        this(   cliente.getId(),
                cliente.getNome(),
                cliente.getTelefone());

    }
}
