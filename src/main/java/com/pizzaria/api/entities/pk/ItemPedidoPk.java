package com.pizzaria.api.entities.pk;

import java.io.Serial;
import java.io.Serializable;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Embeddable
public class ItemPedidoPk implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private Long produtoId;
    private Long pedidoId;

}
