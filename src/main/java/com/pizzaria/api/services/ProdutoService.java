package com.pizzaria.api.services;

import com.pizzaria.api.dto_created.CreatedProdutoDTO;
import com.pizzaria.api.dto_response.ProdutoDTO;
import com.pizzaria.api.entities.Categoria;
import com.pizzaria.api.entities.Produto;
import com.pizzaria.api.exceptions.RecursoNaoEncontradoException;
import com.pizzaria.api.repositories.CategoriaRepository;
import com.pizzaria.api.repositories.ProdutoRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProdutoService {
    private final ProdutoRepository produtoRepository;
    private final CategoriaRepository categoriaRepository;

    public ProdutoService(ProdutoRepository produtoRepository, CategoriaRepository categoriaRepository) {
        this.produtoRepository = produtoRepository;
        this.categoriaRepository = categoriaRepository;
    }

    @Transactional
    public Set<ProdutoDTO> createProduto(CreatedProdutoDTO dto) {
        Categoria categoria = categoriaRepository.findById(dto.categoriaId())
                .orElseThrow(() -> new RecursoNaoEncontradoException("Categoria com id " + dto.categoriaId() + " não encontrada"));
        for (ProdutoDTO produtoDTO : dto.produtos()) {
            Produto produtoNovo = new Produto(produtoDTO.nome(), produtoDTO.preco(), Instant.now());
            if (categoria.addProduto(produtoNovo)) {
                produtoRepository.save(produtoNovo);
            }
        }
        return categoriaRepository.save(categoria).getProdutos()
                .stream().map(ProdutoDTO::new).collect(Collectors.toSet());
    }

    public ProdutoDTO findById(Long id) {
        return produtoRepository.findById(id)
                .map(ProdutoDTO::new)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Produto com id " + id + " não encontrado"));
    }

    public Page<ProdutoDTO> findAll(@PageableDefault(size = 10) Pageable pageable) {
        Page<Produto> dados = produtoRepository.findAll(pageable);
        return dados.map(ProdutoDTO::new);
    }

    @Transactional
    public ProdutoDTO updateProduto(Long id, String nome, BigDecimal preco) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Produto com id " + id + " não encontrado"));
        if (nome != null && !nome.isBlank()) {
            produto.setNome(nome);
        }
        if (preco != null) {
            produto.setPreco(preco);
        }
        produto = produtoRepository.save(produto);
        return new ProdutoDTO(produto);
    }
}
