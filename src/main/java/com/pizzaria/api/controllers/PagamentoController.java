package com.pizzaria.api.controllers;

import com.pizzaria.api.dto_response.PagamentoDTO;
import com.pizzaria.api.dto_response.RespostaDeSucessoDTO;
import com.pizzaria.api.enums.StatusPagamento;
import com.pizzaria.api.enums.TipoPagamento;
import com.pizzaria.api.services.PagamentoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Pagamentos", description = "Endpoints para gerenciamento de pagamentos")
@RestController
@RequestMapping("/pagamentos")
public class PagamentoController {
    private final PagamentoService pagamentoService;

    public PagamentoController(PagamentoService pagamentoService) {
        this.pagamentoService = pagamentoService;
    }

    @Operation(summary = "Buscar pagamento por ID")
    @GetMapping("/{id}")
    public ResponseEntity<RespostaDeSucessoDTO<PagamentoDTO>> findById(@PathVariable Long id) {
        PagamentoDTO pagamento = pagamentoService.findById(id);
        return ResponseEntity.ok(new RespostaDeSucessoDTO<>(200, "Pagamento encontrado", "pagamento", pagamento));
    }

    @Operation(summary = "Listar todos os pagamentos")
    @GetMapping
    public ResponseEntity<RespostaDeSucessoDTO<Page<PagamentoDTO>>> findAll(@PageableDefault Pageable pageable) {
        Page<PagamentoDTO> pagamentos = pagamentoService.findAll(pageable);
        return ResponseEntity.ok(new RespostaDeSucessoDTO<>(200, "Todos os pagamentos", "pagamentos", pagamentos));
    }

    @Operation(summary = "Atualizar status ou tipo do pagamento")
    @PatchMapping("/{id}")
    public ResponseEntity<RespostaDeSucessoDTO<PagamentoDTO>> updatePagamento(
            @PathVariable Long id,
            @RequestParam(required = false) StatusPagamento statusPagamento,
            @RequestParam(required = false) TipoPagamento tipoPagamento) {
        PagamentoDTO pagamento = pagamentoService.updatePagamento(id, statusPagamento, tipoPagamento);
        return ResponseEntity.ok(new RespostaDeSucessoDTO<>(200, "Pagamento atualizado", "pagamento", pagamento));
    }
}
