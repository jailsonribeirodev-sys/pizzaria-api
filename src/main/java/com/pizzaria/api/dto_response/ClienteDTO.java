package com.pizzaria.api.dto_response;

import com.pizzaria.api.entities.Cliente;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record ClienteDTO(
        Long idCliente,
        @NotBlank(message = "Nome é obrigatório")
        String nome,
        @NotBlank(message = "CPF ou CNPJ é obrigatório")
        String cpfOuCnpj,
        @NotBlank(message = "Email é obrigatório")
        @Email(message = "Email inválido")
        String email,
        @NotBlank(message = "Telefone é obrigatório")
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
