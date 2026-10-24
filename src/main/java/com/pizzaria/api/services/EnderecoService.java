package com.pizzaria.api.services;

import com.pizzaria.api.dto_created.EnderecoDTO;
import com.pizzaria.api.dto_created.EnderecoCreatedDTO;
import com.pizzaria.api.dto_response.EnderecoResponseDTO;
import com.pizzaria.api.entities.Cliente;
import com.pizzaria.api.entities.Endereco;
import com.pizzaria.api.exceptions.RecursoNaoEncontradoException;
import com.pizzaria.api.repositories.ClienteRepository;
import com.pizzaria.api.repositories.EnderecoRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
        Cliente cliente = clienteRepository.findById(endereco.clienteId()).orElseThrow(() -> new RecursoNaoEncontradoException("Cliente com id " + endereco.clienteId() + " não encontrado"));
        for (EnderecoDTO dto : endereco.enderecos()) {
            Endereco endecoNovo = new Endereco(dto.rua(), dto.numero(), dto.bairro(), dto.cidade(), dto.estado(), dto.cep(), dto.complemento());
            if (cliente.addEndereco(endecoNovo)) {
                enderecoRepository.save(endecoNovo);
            }
        }

        return clienteRepository.save(cliente).getEnderecos()
                .stream().map(EnderecoResponseDTO::new).collect(Collectors.toSet());
    }

    public EnderecoResponseDTO findById(Long id) {
        return enderecoRepository.findById(id)
                .map(EnderecoResponseDTO::new)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Endereço com id " + id + " não encontrado"));
    }

    public Page<EnderecoResponseDTO> findAll(@PageableDefault(size = 10) Pageable pageable) {
        Page<Endereco> dados = enderecoRepository.findAll(pageable);
        return dados.map(EnderecoResponseDTO::new);
    }

    @Transactional
    public EnderecoResponseDTO updateEndereco(Long id, EnderecoDTO dto) {
        Endereco endereco = enderecoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Endereço com id " + id + " não encontrado"));
        if (dto.rua() != null && !dto.rua().isBlank()) {
            endereco.setRua(dto.rua());
        }
        if (dto.numero() != null && !dto.numero().isBlank()) {
            endereco.setNumero(dto.numero());
        }
        if (dto.bairro() != null && !dto.bairro().isBlank()) {
            endereco.setBairro(dto.bairro());
        }
        if (dto.cidade() != null && !dto.cidade().isBlank()) {
            endereco.setCidade(dto.cidade());
        }
        if (dto.estado() != null && !dto.estado().isBlank()) {
            endereco.setEstado(dto.estado());
        }
        if (dto.cep() != null && !dto.cep().isBlank()) {
            endereco.setCep(dto.cep());
        }
        if (dto.complemento() != null) {
            endereco.setComplemento(dto.complemento());
        }
        endereco = enderecoRepository.save(endereco);
        return new EnderecoResponseDTO(endereco);
    }
}
