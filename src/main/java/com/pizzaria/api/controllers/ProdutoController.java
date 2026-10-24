package com.pizzaria.api.controllers;

import com.pizzaria.api.dto_created.CreatedProdutoDTO;
import com.pizzaria.api.dto_response.ProdutoDTO;
import com.pizzaria.api.dto_response.RespostaDeSucessoDTO;
import com.pizzaria.api.services.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Set;

@Tag(name = "Produtos", description = "Endpoints para gerenciamento de produtos")
@RestController
@RequestMapping("/produtos")
public class ProdutoController {
    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @Operation(summary = "Criar novos produtos")
    @PostMapping
    public ResponseEntity<RespostaDeSucessoDTO<Set<ProdutoDTO>>> createProduto(@Valid @RequestBody CreatedProdutoDTO dto) {
        Set<ProdutoDTO> produtos = produtoService.createProduto(dto);
        return ResponseEntity.ok(new RespostaDeSucessoDTO<>(200, "Produtos criados", "produtos", produtos));
    }

    @Operation(summary = "Buscar produto por ID")
    @GetMapping("/{id}")
    public ResponseEntity<RespostaDeSucessoDTO<ProdutoDTO>> findById(@PathVariable Long id) {
        ProdutoDTO produto = produtoService.findById(id);
        return ResponseEntity.ok(new RespostaDeSucessoDTO<>(200, "Produto encontrado", "produto", produto));
    }

    @Operation(summary = "Listar todos os produtos")
    @GetMapping
    public ResponseEntity<RespostaDeSucessoDTO<Page<ProdutoDTO>>> findAll(@PageableDefault Pageable pageable) {
        Page<ProdutoDTO> produtos = produtoService.findAll(pageable);
        return ResponseEntity.ok(new RespostaDeSucessoDTO<>(200, "Todos os produtos", "produtos", produtos));
    }

    @Operation(summary = "Atualizar dados do produto")
    @PatchMapping("/{id}")
    public ResponseEntity<RespostaDeSucessoDTO<ProdutoDTO>> updateProduto(
            @PathVariable Long id,
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) BigDecimal preco) {
        ProdutoDTO produto = produtoService.updateProduto(id, nome, preco);
        return ResponseEntity.ok(new RespostaDeSucessoDTO<>(200, "Produto atualizado", "produto", produto));
    }
}
