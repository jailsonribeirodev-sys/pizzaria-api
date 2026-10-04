package com.pizzaria.api.entities.pk;

import java.io.Serial;
import java.io.Serializable;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Embeddable
public class ItemPedidoPk implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private Long produtoId;
    private Long pedidoId;

    public ItemPedidoPk() {
    }

}
