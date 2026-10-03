package com.pizzaria.api.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name = "clientes")
public class Cliente implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "Nome é obrigatório")
    private String nome;
    @NotNull(message = "Telefone é obrigatório")
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
