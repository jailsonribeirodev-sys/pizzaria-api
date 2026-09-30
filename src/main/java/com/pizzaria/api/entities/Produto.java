package com.pizzaria.api.entities;

import java.math.BigDecimal;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class Produto {
    private Long id;
    private String nome;
    private BigDecimal preco;
    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    public Produto() {

    }

    public Produto(Long id, String nome, BigDecimal preco, Categoria categoria) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.categoria = categoria;
    }
}
