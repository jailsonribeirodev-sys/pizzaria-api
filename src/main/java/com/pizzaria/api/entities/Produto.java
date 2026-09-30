package com.pizzaria.api.entities;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
public class Produto {
    private Long id;
    private String nome;
    private BigDecimal preco;
    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;
    @OneToMany(mappedBy = "produto")
    private Set<ItemPedido> itens = new HashSet<>();

    public Produto() {

    }

    public Produto(Long id, String nome, BigDecimal preco, Categoria categoria) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.categoria = categoria;
    }
}
