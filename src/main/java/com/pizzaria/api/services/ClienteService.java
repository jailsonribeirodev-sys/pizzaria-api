package com.pizzaria.api.services;

import com.pizzaria.api.dto_response.ClientResponseDTO;
import com.pizzaria.api.dto_response.ClienteDTO;
import com.pizzaria.api.entities.Cliente;
import com.pizzaria.api.exceptions.RecursoNaoEncontradoException;
import com.pizzaria.api.repositories.ClienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {
    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public ClientResponseDTO findById(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Cliente com id " + id + " não encontrado"));
        return new ClientResponseDTO(cliente);
    }

    @Transactional
    public ClientResponseDTO createCliente(ClienteDTO dto) {
        Cliente cliente = new Cliente(dto.nome(), dto.telefone(), dto.cpfOuCnpj(), dto.email());
        cliente = clienteRepository.save(cliente);
        return new ClientResponseDTO(cliente);
    }

    public Page<ClientResponseDTO> findAll(@PageableDefault(size = 10) Pageable pageable) {
        Page<Cliente> dados = clienteRepository.findAll(pageable);
        return dados.map(ClientResponseDTO::new);
    }

    @Transactional
    public ClientResponseDTO updateCliente(Long id, String nome, String telefone, String email) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Cliente com id " + id + " não encontrado"));
        if (nome != null && !nome.isBlank()) {
            cliente.setNome(nome);
        }
        if (telefone != null && !telefone.isBlank()) {
            cliente.setTelefone(telefone);
        }
        if (email != null && !email.isBlank()) {
            cliente.setEmail(email);
        }
        cliente = clienteRepository.save(cliente);
        return new ClientResponseDTO(cliente);
    }
}
