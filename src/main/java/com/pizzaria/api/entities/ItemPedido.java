package com.pizzaria.api.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import com.pizzaria.api.entities.pk.ItemPedidoPk;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "item_pedido")

public class ItemPedido implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "Quantidade é obrigatória")
    private Integer quantidade;
    @NotNull(message = "Preço é obrigatório")
    private BigDecimal preco;
    private String observacao;

    @EmbeddedId
    @EqualsAndHashCode.Include
    private ItemPedidoPk id = new ItemPedidoPk();

    @ManyToOne
    @MapsId("produtoId")
    @JoinColumn(name = "produto_id")
    private Produto produto;
    @ManyToOne
    @MapsId("pedidoId")
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

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

}
