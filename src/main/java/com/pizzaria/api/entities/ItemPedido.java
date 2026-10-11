package com.pizzaria.api.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

import com.pizzaria.api.entities.pk.ItemPedidoPk;

import jakarta.persistence.*;

@Entity
@Table(name = "item_pedido")
public class ItemPedido implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer quantidade;
    private BigDecimal preco;
    private String observacao;

    @EmbeddedId
    private ItemPedidoPk id = new ItemPedidoPk();

    @ManyToOne
    @MapsId("produtoId")
    @JoinColumn(name = "produto_id")
    private Produto produto;
    @ManyToOne
    @MapsId("pedidoId")
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

    public ItemPedido() {
    }

    public ItemPedido(Produto produto, Pedido pedido, Integer quantidade, BigDecimal preco, String observacao) {
        this.pedido = pedido;
        this.produto = produto;
        if (produto != null)
            this.id.setProdutoId(produto.getId());
        if (pedido != null)
            this.id.setPedidoId(pedido.getId());
        this.quantidade = quantidade;
        this.preco = preco;
        this.observacao = observacao;
    }

    public ItemPedidoPk getId() {
        return id;
    }

    public void setId(ItemPedidoPk id) {
        this.id = id;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }
    @Column(name = "subtotal")
    public BigDecimal getSubtotal(){

        return preco.multiply(new BigDecimal(quantidade));
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemPedido that = (ItemPedido) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
