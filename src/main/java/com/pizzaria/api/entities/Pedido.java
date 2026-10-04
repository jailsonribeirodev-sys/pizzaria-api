package com.pizzaria.api.entities;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import com.pizzaria.api.enums.StatusPedido;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "pedido")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private Set<ItemPedido> itens = new HashSet<>();
    private BigDecimal total;
    private Integer status;
    @OneToOne(mappedBy = "pedido", cascade = CascadeType.ALL)
    private Pagamento pagamento;
    private Instant dataHora;
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    public Pedido(Long id, Cliente cliente, BigDecimal total, StatusPedido status, Instant dataHora) {
        this.id = id;
        this.cliente = cliente;
        this.total = total;
        setStatus(status);
        this.dataHora = dataHora;
    }

    public StatusPedido getStatus() {
        return status != null ? StatusPedido.valueOf(status) : null;
    }

    public void setStatus(StatusPedido status) {
        this.status = (status != null) ? status.getCode() : null;
    }

}
