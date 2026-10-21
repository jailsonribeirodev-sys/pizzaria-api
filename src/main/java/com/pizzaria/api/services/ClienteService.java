package com.pizzaria.api.services;

import com.pizzaria.api.dto_response.ClientResponseDTO;
import com.pizzaria.api.repositories.ClienteRepository;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {
    private final ClienteRepository clienteRepository;


    public ClienteService(ClienteRepository clienteRepository) {

        this.clienteRepository = clienteRepository;

    }
    public ClientResponseDTO findById(Long id) {
        var cliente = clienteRepository.findById(id).orElse(null);
        assert cliente != null;
        return new ClientResponseDTO(cliente);

    }





}
