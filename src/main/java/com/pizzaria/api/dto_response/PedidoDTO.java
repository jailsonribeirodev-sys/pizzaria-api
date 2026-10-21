package com.pizzaria.api.dto_response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pizzaria.api.dto_created.EnderecoDTO;
import com.pizzaria.api.entities.Pedido;
import com.pizzaria.api.enums.StatusPedido;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Set;
import java.util.stream.Collectors;

public record PedidoDTO(
        Long idPedido,
        StatusPedido status,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "America/Fortaleza")
        Instant dataHora,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "America/Fortaleza")
        Instant dataAtualizacao,
        ClienteDTO cliente,
        EnderecoDTO endereco,
        PagamentoDTO pagamento,
        Set<ItemPedidoDTO> itensPedido,
         BigDecimal  total
) {
    public PedidoDTO(Pedido pedido) {
        this(pedido.getId(),
                pedido.getStatus(),
                pedido.getDataHora(),
                pedido.getDataAtualizacao(),
                new ClienteDTO(pedido.getCliente()),
                new EnderecoDTO(pedido.getEndereco()),
                new PagamentoDTO(pedido.getPagamento()),
                pedido.getItens().stream().map(ItemPedidoDTO::new).collect(Collectors.toUnmodifiableSet()),
                pedido.getTotal()
        );
    }
}
