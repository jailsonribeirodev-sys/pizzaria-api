package com.pizzaria.api.dto;

import com.pizzaria.api.entities.Pagamento;
import com.pizzaria.api.entities.Pedido;
import com.pizzaria.api.enums.StatusPedido;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Set;
import java.util.stream.Collectors;

public record PedidoDTO(
        Long id,
        StatusPedido status,
        Instant dataHora,
        Instant dataAtualizacao,
        ClienteDTO cliente,
        PagamentoDTO pagamento,
        Set<ItemPedidoDTO> itens,
         BigDecimal total
) {
    public PedidoDTO(Pedido pedido) {
        this(pedido.getId(),
                pedido.getStatus(),
                pedido.getDataHora(),
                pedido.getDataAtualizacao(),
                pedido.getCliente() != null ? new ClienteDTO(pedido.getCliente()) : null,
                pedido.getPagamento() != null ? new PagamentoDTO(pedido.getPagamento()): null,
                pedido.getItens() != null ?  pedido.getItens().stream().map(ItemPedidoDTO::new).collect(Collectors.toSet()):Set.of(),
                pedido.getTotal()
        );
    }
}
