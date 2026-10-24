package com.pizzaria.api.controllers;

import com.pizzaria.api.dto_response.ClientResponseDTO;
import com.pizzaria.api.dto_response.ClienteDTO;
import com.pizzaria.api.dto_response.RespostaDeSucessoDTO;
import com.pizzaria.api.services.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Clientes", description = "Endpoints para gerenciamento de clientes")
@RestController
@RequestMapping("/clientes")
public class ClienteController {
    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @Operation(summary = "Criar um novo cliente")
    @PostMapping
    public ResponseEntity<RespostaDeSucessoDTO<ClientResponseDTO>> createCliente(@RequestBody ClienteDTO dto) {
        ClientResponseDTO cliente = clienteService.createCliente(dto);
        return ResponseEntity.ok(new RespostaDeSucessoDTO<>(200, "Cliente criado", "cliente", cliente));
    }

    @Operation(summary = "Buscar cliente por ID")
    @GetMapping("/{id}")
    public ResponseEntity<RespostaDeSucessoDTO<ClientResponseDTO>> findById(@PathVariable Long id) {
        ClientResponseDTO cliente = clienteService.findById(id);
        return ResponseEntity.ok(new RespostaDeSucessoDTO<>(200, "Cliente encontrado", "cliente", cliente));
    }

    @Operation(summary = "Listar todos os clientes")
    @GetMapping
    public ResponseEntity<RespostaDeSucessoDTO<Page<ClientResponseDTO>>> findAll(@PageableDefault Pageable pageable) {
        Page<ClientResponseDTO> clientes = clienteService.findAll(pageable);
        return ResponseEntity.ok(new RespostaDeSucessoDTO<>(200, "Todos os clientes", "clientes", clientes));
    }

    @Operation(summary = "Atualizar dados do cliente")
    @PatchMapping("/{id}")
    public ResponseEntity<RespostaDeSucessoDTO<ClientResponseDTO>> updateCliente(
            @PathVariable Long id,
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) String telefone,
            @RequestParam(required = false) String email) {
        ClientResponseDTO cliente = clienteService.updateCliente(id, nome, telefone, email);
        return ResponseEntity.ok(new RespostaDeSucessoDTO<>(200, "Cliente atualizado", "cliente", cliente));
    }
}
