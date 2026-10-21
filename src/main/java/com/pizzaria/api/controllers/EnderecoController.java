package com.pizzaria.api.controllers;

import com.pizzaria.api.dto_created.EnderecoCreatedDTO;
import com.pizzaria.api.dto_created.responseDtoFormat.EnderecoFormatDTO;
import com.pizzaria.api.dto_response.EnderecoResponseDTO;
import com.pizzaria.api.services.ClienteService;
import com.pizzaria.api.services.EnderecoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {
    private final EnderecoService enderecoService;
    private final ClienteService clienteService;

    public EnderecoController(EnderecoService enderecoService, ClienteService clienteService ) {
        this.clienteService = clienteService;
        this.enderecoService = enderecoService;
    }
    @PostMapping
    public ResponseEntity<EnderecoFormatDTO> createEndereco(@RequestBody EnderecoCreatedDTO enderecoDTO) {

        Set<EnderecoResponseDTO> enderecoResponseDTO = enderecoService.save(enderecoDTO);
        EnderecoFormatDTO enderecoFormat = new EnderecoFormatDTO(enderecoResponseDTO);
      return   ResponseEntity.ok(enderecoFormat);
    }


}
