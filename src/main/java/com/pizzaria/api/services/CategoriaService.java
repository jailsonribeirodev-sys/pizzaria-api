package com.pizzaria.api.services;

import com.pizzaria.api.dto_response.CategoriaDTO;
import com.pizzaria.api.entities.Categoria;
import com.pizzaria.api.exceptions.RecursoNaoEncontradoException;
import com.pizzaria.api.repositories.CategoriaRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {
    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @Transactional
    public CategoriaDTO createCategoria(CategoriaDTO dto) {
        Categoria categoria = new Categoria(dto.nome(), dto.descricao());
        categoria = categoriaRepository.save(categoria);
        return new CategoriaDTO(categoria);
    }

    public CategoriaDTO findById(Long id) {
        return categoriaRepository.findById(id)
                .map(CategoriaDTO::new)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Categoria com id " + id + " não encontrada"));
    }

    public Page<CategoriaDTO> findAll(@PageableDefault(size = 10) Pageable pageable) {
        Page<Categoria> dados = categoriaRepository.findAll(pageable);
        return dados.map(CategoriaDTO::new);
    }

    @Transactional
    public CategoriaDTO updateCategoria(Long id, String nome, String descricao) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Categoria com id " + id + " não encontrada"));
        if (nome != null && !nome.isBlank()) {
            categoria.setNome(nome);
        }
        if (descricao != null && !descricao.isBlank()) {
            categoria.setDescricao(descricao);
        }
        categoria = categoriaRepository.save(categoria);
        return new CategoriaDTO(categoria);
    }
}
