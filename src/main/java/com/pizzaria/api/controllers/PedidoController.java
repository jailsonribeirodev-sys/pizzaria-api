package com.pizzaria.api.controllers;

import com.pizzaria.api.dto_created.PedidoCreatedDTO;
import com.pizzaria.api.dto_response.PedidoDTO;
import com.pizzaria.api.dto_response.RespostaDeSucessoDTO;
import com.pizzaria.api.enums.StatusPagamento;
import com.pizzaria.api.enums.StatusPedido;
import com.pizzaria.api.exceptions.RecursoNaoEncontradoException;
import com.pizzaria.api.services.PedidoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Pedidos", description = "Endpoints para gerenciamento de pedidos")
@RestController
@RequestMapping("/pedidos")
public class PedidoController {
    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @Operation(summary = "Criar um novo pedido")
    @PostMapping
    public ResponseEntity<RespostaDeSucessoDTO<PedidoDTO>> createdPedido(@Valid @RequestBody PedidoCreatedDTO dto) {
        PedidoDTO pedido = pedidoService.createdPedido(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new RespostaDeSucessoDTO<>(201, "Status pedido e status pagamento atualizados ", "pedido", pedido));
    }

    @Operation(summary = "Atualizar status do pedido")
    @PatchMapping("/{id}/")
    public ResponseEntity<RespostaDeSucessoDTO<PedidoDTO>> updatePedido(@PathVariable Long id, @RequestParam StatusPedido status, @RequestParam StatusPagamento statusPagamento) {
        PedidoDTO pedido = pedidoService.updatePedido(id, status, statusPagamento);
        return ResponseEntity.ok(new RespostaDeSucessoDTO<>(200, "Status pedido e status pagamento atualizados ", "pedido", pedido));
    }

    @Operation(summary = "Buscar pedido por ID")
    @GetMapping("/{id}")
    public ResponseEntity<RespostaDeSucessoDTO<PedidoDTO>> findById(@PathVariable Long id) {
        PedidoDTO pedido = pedidoService.findById(id);
        return ResponseEntity.ok(new RespostaDeSucessoDTO<>(200, "Pedido encontrado", "pedido", pedido));
    }

    @Operation(summary = "Listar todos os pedidos")
    @GetMapping
    public ResponseEntity<RespostaDeSucessoDTO<Page<PedidoDTO>>> findAll(@PageableDefault Pageable pageable) {
        Page<PedidoDTO> pedidoDTO = pedidoService.findAll(pageable);
        return ResponseEntity.ok(new RespostaDeSucessoDTO<>(200, "Todos os pedidos", "pedidos", pedidoDTO));


    }
}
