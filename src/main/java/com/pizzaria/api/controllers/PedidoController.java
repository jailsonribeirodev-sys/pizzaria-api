package com.pizzaria.api.controllers;

import com.pizzaria.api.dto_created.responseDtoFormat.PedidoFormatDTO;
import com.pizzaria.api.dto_response.PedidoDTO;
import com.pizzaria.api.dto_created.PedidoCreatedDTO;
import com.pizzaria.api.dto_created.responseDtoFormat.PedidoFormatLIstDTO;
import com.pizzaria.api.enums.StatusPagamento;
import com.pizzaria.api.enums.StatusPedido;
import com.pizzaria.api.services.PedidoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<PedidoDTO> createdPedido(@RequestBody PedidoCreatedDTO dto) {
        PedidoDTO pedido = pedidoService.createdPedido(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(pedido);
    }
    @Operation(summary = "Atualizar status do pedido")
    @PatchMapping("/{id}/")
    public ResponseEntity<PedidoDTO> updatePedido(@PathVariable Long id, @RequestParam StatusPedido status, @RequestParam StatusPagamento statusPagamento) {
            return ResponseEntity.ok(pedidoService.updatePedido(id, status,  statusPagamento));
    }

    @Operation(summary = "Buscar pedido por ID")
    @GetMapping("/{id}")
    public ResponseEntity<PedidoFormatDTO> findById(@PathVariable Long id) {
      return  pedidoService.findById(id).map(pedido -> ResponseEntity.ok(new PedidoFormatDTO(pedido)))
              .orElseGet(()->  ResponseEntity.notFound().build());
    }

    @Operation(summary = "Listar todos os pedidos")
    @GetMapping
    public ResponseEntity<PedidoFormatLIstDTO> findAll() {
        List<PedidoDTO> pedidoDTO = pedidoService.findAll();
        PedidoFormatLIstDTO pedidoFormatDTO = new PedidoFormatLIstDTO(pedidoDTO);
        return ResponseEntity.ok(pedidoFormatDTO);


    }
}
