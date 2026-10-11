package com.pizzaria.api.services;

import com.pizzaria.api.repositories.EnderecoRepository;
import org.springframework.stereotype.Service;

@Service
public class EnderecoService {
  private final   EnderecoRepository enderecoRepository;
    public EnderecoService (EnderecoRepository enderecoRepository) {
        this.enderecoRepository = enderecoRepository;
    }
}
