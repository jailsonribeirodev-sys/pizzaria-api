package com.pizzaria.api.controllers;

import com.pizzaria.api.dto_created.EnderecoCreatedDTO;
import com.pizzaria.api.dto_created.EnderecoDTO;
import com.pizzaria.api.dto_response.EnderecoResponseDTO;
import com.pizzaria.api.dto_response.RespostaDeSucessoDTO;
import com.pizzaria.api.services.ClienteService;
import com.pizzaria.api.services.EnderecoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Tag(name = "Endereços", description = "Endpoints para gerenciamento de endereços")
@RestController
@RequestMapping("/enderecos")
public class EnderecoController {
    private final EnderecoService enderecoService;
    private final ClienteService clienteService;

    public EnderecoController(EnderecoService enderecoService, ClienteService clienteService) {
        this.clienteService = clienteService;
        this.enderecoService = enderecoService;
    }

    @Operation(summary = "Criar endereços para um cliente")
    @PostMapping
    public ResponseEntity<RespostaDeSucessoDTO<Set<EnderecoResponseDTO>>> createEndereco(@Valid @RequestBody EnderecoCreatedDTO enderecoDTO) {
        Set<EnderecoResponseDTO> enderecoResponseDTO = enderecoService.save(enderecoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(new RespostaDeSucessoDTO<>(201, "Enderecos criados", "enderecos", enderecoResponseDTO));
    }

    @Operation(summary = "Buscar endereço por ID")
    @GetMapping("/{id}")
    public ResponseEntity<RespostaDeSucessoDTO<EnderecoResponseDTO>> findById(@PathVariable Long id) {
        EnderecoResponseDTO endereco = enderecoService.findById(id);
        return ResponseEntity.ok(new RespostaDeSucessoDTO<>(200, "Endereço encontrado", "endereco", endereco));
    }

    @Operation(summary = "Listar todos os endereços")
    @GetMapping
    public ResponseEntity<RespostaDeSucessoDTO<Page<EnderecoResponseDTO>>> findAll(@PageableDefault Pageable pageable) {
        Page<EnderecoResponseDTO> enderecos = enderecoService.findAll(pageable);
        return ResponseEntity.ok(new RespostaDeSucessoDTO<>(200, "Todos os endereços", "enderecos", enderecos));
    }

    @Operation(summary = "Atualizar dados do endereço")
    @PatchMapping("/{id}")
    public ResponseEntity<RespostaDeSucessoDTO<EnderecoResponseDTO>> updateEndereco(
            @PathVariable Long id,
            @Valid @RequestBody EnderecoDTO dto) {
        EnderecoResponseDTO endereco = enderecoService.updateEndereco(id, dto);
        return ResponseEntity.ok(new RespostaDeSucessoDTO<>(200, "Endereço atualizado", "endereco", endereco));
    }
}
