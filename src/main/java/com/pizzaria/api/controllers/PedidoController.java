package com.pizzaria.api.controllers;

import com.pizzaria.api.dto.PedidoDTO;
import com.pizzaria.api.dto_created.PedidoCreatedDTO;
import com.pizzaria.api.enums.StatusPedido;
import com.pizzaria.api.services.PedidoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping
    public ResponseEntity<PedidoDTO> criarPedido(@RequestBody PedidoCreatedDTO dto) {
        PedidoDTO pedido = pedidoService.criarPedido(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(pedido);
    }
    @PatchMapping("/{id}/status")
    public ResponseEntity<PedidoDTO> atualizarPedido(@PathVariable Long id, @RequestParam StatusPedido status) {
            return ResponseEntity.ok(pedidoService.updatePedido(id, status));
    }
}
