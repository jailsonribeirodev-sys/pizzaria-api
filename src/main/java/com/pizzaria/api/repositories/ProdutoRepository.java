package com.pizzaria.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pizzaria.api.entities.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
