package com.pizzaria.api.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
public class Cliente {
    private Long id;
    private String nome;
    private String telefone;
    @OneToMany(mappedBy = "cliente")
    private List<Pedido> pedido = new ArrayList<>();
    @OneToMany(mappedBy = "cliente")
    private List<Endereco> endereco = new ArrayList<>();

    public Cliente() {

    }

    public Cliente(Long id, String nome, String telefone) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
    }

}
