package com.pizzaria.api.services;

import ch.qos.logback.core.status.Status;
import com.pizzaria.api.dto.PedidoDTO;
import com.pizzaria.api.dto_created.ItemPedidoCreatedDTO;
import com.pizzaria.api.dto_created.PedidoCreatedDTO;
import com.pizzaria.api.entities.*;
import com.pizzaria.api.enums.StatusPagamento;
import com.pizzaria.api.enums.StatusPedido;
import com.pizzaria.api.enums.TipoPagamento;
import com.pizzaria.api.repositories.ClienteRepository;
import com.pizzaria.api.repositories.PedidoRepository;
import com.pizzaria.api.repositories.ProdutoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class PedidoService {
    private final PedidoRepository pedidoRepository;
    private final ClienteRepository clienteRepository;
    private final ProdutoRepository produtoRepository;

    public PedidoService(PedidoRepository pedidoRepository,
                         ClienteRepository clienteRepository,
                         ProdutoRepository produtoRepository) {
        this.clienteRepository = clienteRepository;
        this.pedidoRepository = pedidoRepository;
        this.produtoRepository = produtoRepository;
    }

    @Transactional
    public PedidoDTO criarPedido(PedidoCreatedDTO dto) {
        Cliente cliente = clienteRepository.findById(dto.clienteId()).orElseThrow(() -> new RuntimeException("Cliente não achado!"));
        Pedido pedido = new Pedido();
        pedido.setDataHora(Instant.now());
        pedido.setStatus(StatusPedido.EM_PREPARO);
        pedido.setCliente(cliente);
        if (dto.itens() != null)
            for (ItemPedidoCreatedDTO itemPedido : dto.itens()) {
                Produto produto = produtoRepository.findById(itemPedido.produtoId()).orElseThrow(() -> new RuntimeException("não achado"));
                ItemPedido item = new ItemPedido(produto, pedido, itemPedido.quantidade(), produto.getPreco(), "pedido ok!");
                pedido.getItens().add(item);
            }
        Pagamento pagamento = new Pagamento(pedido, TipoPagamento.CARTAO_CREDITO, StatusPagamento.PENDENTE, Instant.now());
        pedido.setPagamento(pagamento);
        pedido = pedidoRepository.save(pedido);


        return new PedidoDTO(pedido);
    }
    public PedidoDTO updatePedido(Long id, StatusPedido status) {
        Pedido pedido = pedidoRepository.findById(id).orElseThrow(()-> new RuntimeException("Pedido não encontrado!"));
        pedido.setStatus(status);
        pedido = pedidoRepository.save(pedido);
        return new PedidoDTO(pedido);
    }

}
