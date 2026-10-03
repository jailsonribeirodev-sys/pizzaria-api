package com.pizzaria.api.entities;

import java.time.Instant;

import com.pizzaria.api.enums.TipoPagamento;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import main.java.com.pizzaria.api.enums.StatusPagamento;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "pagamento")
public class Pagamento {
    @Id
    @EqualsAndHashCode.Include
    private Long id;
    @MapsId
    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;
    private Integer tipoPagamento;
    private Integer statusPagamento;
    private Instant dataHora;

    public Pagamento(Pedido pedido, TipoPagamento tipoPagamento, StatusPagamento statusPagamento, Instant dataHora) {
        this.pedido = (pedido != null) ? pedido.getId() : null;
        this.tipoPagamento = (tipoPagamento != null) ? tipoPagamento.getCode() : null;
        this.statusPagamento = (statusPagamento != null) ? statusPagamento.getCode() : null;
        this.dataHora = dataHora;
    }

}
