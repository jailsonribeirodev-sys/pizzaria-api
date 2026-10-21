package com.pizzaria.api.dto_response;

import com.pizzaria.api.entities.Cliente;

public record ClienteDTO(
        Long idCliente,
        String nome,
        String cpfOuCnpj,
        String email,
        String telefone

) {
    public ClienteDTO(Cliente cliente) {
        this(
                cliente.getId(),
                cliente.getNome(),
                cliente.getCpfOuCnpj(),
                cliente.getEmail(),
                cliente.getTelefone()



        );
    }
}
