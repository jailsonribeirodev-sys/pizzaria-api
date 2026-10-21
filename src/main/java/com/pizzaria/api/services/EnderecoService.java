package com.pizzaria.api.services;

import com.pizzaria.api.dto_created.EnderecoDTO;
import com.pizzaria.api.dto_created.EnderecoCreatedDTO;
import com.pizzaria.api.dto_response.EnderecoResponseDTO;
import com.pizzaria.api.entities.Cliente;
import com.pizzaria.api.entities.Endereco;

import com.pizzaria.api.repositories.ClienteRepository;
import com.pizzaria.api.repositories.EnderecoRepository;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EnderecoService {
    private final EnderecoRepository enderecoRepository;
    private final ClienteRepository clienteRepository;

    public EnderecoService(EnderecoRepository enderecoRepository, ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
        this.enderecoRepository = enderecoRepository;
    }

    @Transactional
    public Set<EnderecoResponseDTO> save(EnderecoCreatedDTO endereco) {
        Cliente cliente = clienteRepository.findById(endereco.clienteId()).orElseThrow(()-> new RuntimeException("Cliente não encontrado"));
        for (EnderecoDTO dto : endereco.enderecos()) {
            Endereco endecoNovo = new Endereco(dto.rua(), dto.numero(), dto.bairro(), dto.cidade(), dto.estado(), dto.cep(), dto.complemento());
            if (cliente.addEndereco(endecoNovo)) {
                enderecoRepository.save(endecoNovo);
            }

        }

        return clienteRepository.save(cliente).getEnderecos()
                .stream().map(EnderecoResponseDTO::new).collect(Collectors.toSet());

    }
}