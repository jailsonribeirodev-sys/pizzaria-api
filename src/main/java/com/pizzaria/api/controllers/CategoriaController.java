package com.pizzaria.api.controllers;

import com.pizzaria.api.dto_response.CategoriaDTO;
import com.pizzaria.api.dto_response.RespostaDeSucessoDTO;
import com.pizzaria.api.services.CategoriaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Categorias", description = "Endpoints para gerenciamento de categorias")
@RestController
@RequestMapping("/categorias")
public class CategoriaController {
    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @Operation(summary = "Criar uma nova categoria")
    @PostMapping
    public ResponseEntity<RespostaDeSucessoDTO<CategoriaDTO>> createCategoria(@Valid @RequestBody CategoriaDTO dto) {
        CategoriaDTO categoria = categoriaService.createCategoria(dto);
        return ResponseEntity.ok(new RespostaDeSucessoDTO<>(200, "Categoria criada", "categoria", categoria));
    }

    @Operation(summary = "Buscar categoria por ID")
    @GetMapping("/{id}")
    public ResponseEntity<RespostaDeSucessoDTO<CategoriaDTO>> findById(@PathVariable Long id) {
        CategoriaDTO categoria = categoriaService.findById(id);
        return ResponseEntity.ok(new RespostaDeSucessoDTO<>(200, "Categoria encontrada", "categoria", categoria));
    }

    @Operation(summary = "Listar todas as categorias")
    @GetMapping
    public ResponseEntity<RespostaDeSucessoDTO<Page<CategoriaDTO>>> findAll(@PageableDefault Pageable pageable) {
        Page<CategoriaDTO> categorias = categoriaService.findAll(pageable);
        return ResponseEntity.ok(new RespostaDeSucessoDTO<>(200, "Todas as categorias", "categorias", categorias));
    }

    @Operation(summary = "Atualizar dados da categoria")
    @PatchMapping("/{id}")
    public ResponseEntity<RespostaDeSucessoDTO<CategoriaDTO>> updateCategoria(
            @PathVariable Long id,
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) String descricao) {
        CategoriaDTO categoria = categoriaService.updateCategoria(id, nome, descricao);
        return ResponseEntity.ok(new RespostaDeSucessoDTO<>(200, "Categoria atualizada", "categoria", categoria));
    }
}
