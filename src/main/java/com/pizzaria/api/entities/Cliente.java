package com.pizzaria.api.entities;

import java.io.Serializable;
import java.util.*;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "cliente")
public class Cliente implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "Nome é obrigatório")
    private String nome;
    @NotNull(message = "Não pode ser vazio")
    private String cpfOuCnpj;
    @NotNull(message = "Obrigatório")
    private String email;
    @NotNull(message = "Telefone é obrigatório")
    private String telefone;
    @OneToMany(mappedBy = "cliente")

    private List<Pedido> pedidos = new ArrayList<>();
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Set<Endereco> enderecos = new HashSet<>();

    public Cliente() {
    }

    public Cliente(String nome, String telefone, String cpfOuCnpj, String email) {

        this.nome = nome;
        this.telefone = telefone;
        this.cpfOuCnpj = cpfOuCnpj;
        this.email = email;


    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public Set<Endereco> getEnderecos() {
        return enderecos;
    }


    public boolean addEndereco(Endereco endereco) {
       boolean ok = this.enderecos.add(endereco);
      if (ok){
          endereco.setCliente(this);
      }
      return ok;
    }

    public void addPedido(Pedido pedido) {
        boolean ok = this.pedidos.add(pedido);
            pedido.setCliente(this);
    }
    public String getCpfOuCnpj() {
        return cpfOuCnpj;
    }

    public void setCpfOuCnpj(String cpfOuCnpj) {
        this.cpfOuCnpj = cpfOuCnpj;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(id, cliente.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
