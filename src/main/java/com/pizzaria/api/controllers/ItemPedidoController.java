package com.pizzaria.api.controllers;

import com.pizzaria.api.dto_response.ItemPedidoDTO;
import com.pizzaria.api.dto_response.RespostaDeSucessoDTO;
import com.pizzaria.api.services.ItemPedidoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Itens do Pedido", description = "Endpoints para gerenciamento de itens do pedido")
@RestController
@RequestMapping("/itens-pedido")
public class ItemPedidoController {
    private final ItemPedidoService itemPedidoService;

    public ItemPedidoController(ItemPedidoService itemPedidoService) {
        this.itemPedidoService = itemPedidoService;
    }

    @Operation(summary = "Buscar item do pedido por ID do pedido e ID do produto")
    @GetMapping("/{pedidoId}/{produtoId}")
    public ResponseEntity<RespostaDeSucessoDTO<ItemPedidoDTO>> findById(
            @PathVariable Long pedidoId,
            @PathVariable Long produtoId) {
        ItemPedidoDTO item = itemPedidoService.findById(pedidoId, produtoId);
        return ResponseEntity.ok(new RespostaDeSucessoDTO<>(200, "Item do pedido encontrado", "itemPedido", item));
    }

    @Operation(summary = "Listar todos os itens de pedido")
    @GetMapping
    public ResponseEntity<RespostaDeSucessoDTO<Page<ItemPedidoDTO>>> findAll(@PageableDefault Pageable pageable) {
        Page<ItemPedidoDTO> itens = itemPedidoService.findAll(pageable);
        return ResponseEntity.ok(new RespostaDeSucessoDTO<>(200, "Todos os itens de pedido", "itensPedido", itens));
    }

    @Operation(summary = "Atualizar quantidade ou observação do item do pedido")
    @PatchMapping("/{pedidoId}/{produtoId}")
    public ResponseEntity<RespostaDeSucessoDTO<ItemPedidoDTO>> updateItemPedido(
            @PathVariable Long pedidoId,
            @PathVariable Long produtoId,
            @RequestParam(required = false) Integer quantidade,
            @RequestParam(required = false) String observacao) {
        ItemPedidoDTO item = itemPedidoService.updateItemPedido(pedidoId, produtoId, quantidade, observacao);
        return ResponseEntity.ok(new RespostaDeSucessoDTO<>(200, "Item do pedido atualizado", "itemPedido", item));
    }
}
