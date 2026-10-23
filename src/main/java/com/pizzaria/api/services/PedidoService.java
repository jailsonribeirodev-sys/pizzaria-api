package com.pizzaria.api.services;

import com.pizzaria.api.dto_created.EnderecoDTO;
import com.pizzaria.api.dto_response.PedidoDTO;
import com.pizzaria.api.dto_created.ItemPedidoCreatedDTO;
import com.pizzaria.api.dto_created.PedidoCreatedDTO;
import com.pizzaria.api.entities.*;
import com.pizzaria.api.enums.StatusPagamento;
import com.pizzaria.api.enums.StatusPedido;
import com.pizzaria.api.exceptions.RecursoNaoEncontradoException;
import com.pizzaria.api.repositories.ClienteRepository;
import com.pizzaria.api.repositories.EnderecoRepository;
import com.pizzaria.api.repositories.PedidoRepository;
import com.pizzaria.api.repositories.ProdutoRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {
    private final PedidoRepository pedidoRepository;
    private final ClienteRepository clienteRepository;
    private final ProdutoRepository produtoRepository;
    private final EnderecoRepository enderecoRepository;
    public PedidoService(PedidoRepository pedidoRepository,
                         ClienteRepository clienteRepository,
                         ProdutoRepository produtoRepository,
                         EnderecoRepository enderecoRepository) {
        this.clienteRepository = clienteRepository;
        this.pedidoRepository = pedidoRepository;
        this.produtoRepository = produtoRepository;
        this.enderecoRepository = enderecoRepository;
    }

    @Transactional
    public PedidoDTO createdPedido(PedidoCreatedDTO dto) {
        Endereco endereco = enderecoRepository.findById(dto.idEndereco()).orElseThrow(() -> new RecursoNaoEncontradoException("Endereço com id " + dto.idEndereco()+" Nao Encontrado" ));
        Cliente cliente = clienteRepository.findById(dto.idCliente()).orElseThrow(() -> new RecursoNaoEncontradoException("Cliente com id " + dto.idEndereco()+" Nao Encontrado" ));
        Pedido pedido = new  Pedido();
        pedido.setDataHora(Instant.now());
        pedido.setStatus(StatusPedido.EM_PREPARO);
        pedido.setEndereco(endereco);
            for(ItemPedidoCreatedDTO itemPedido: dto.itensPedido()) {
                Produto produto = produtoRepository.findById(itemPedido.idProduto()).orElseThrow(() -> new RuntimeException("não achado"));
                ItemPedido item = new ItemPedido(produto, pedido, itemPedido.quantidade(), produto.getPreco());
                item.setObservacao(itemPedido.observacao());
                pedido.getItens().add(item);
            }
        Pagamento pagamento = new Pagamento(pedido, dto.tipoPagamento(), StatusPagamento.PENDENTE, Instant.now());
        pedido.setPagamento(pagamento);
        cliente.addPedido(pedido);
        pedido = pedidoRepository.save(pedido);
        return new PedidoDTO(pedido);

    }
    public PedidoDTO updatePedido(Long id,
                                  StatusPedido status,
                                  StatusPagamento statusPagamento) {
        Pedido findPedidoById = pedidoRepository.findById(id).orElseThrow(()-> new RecursoNaoEncontradoException("Id "+id+" Nao Encontrado" ));
        findPedidoById.setStatus(status);
        findPedidoById.getPagamento().setStatusPagamento(statusPagamento);
        findPedidoById = pedidoRepository.save(findPedidoById);
        return new PedidoDTO(findPedidoById);
    }

    public PedidoDTO findById(Long id) {
        return pedidoRepository.findById(id).map(PedidoDTO::new).orElseThrow(()-> new RecursoNaoEncontradoException("Id "+id+" não encontrado"));
    }

    public Page<PedidoDTO> findAll(@PageableDefault(size = 10) Pageable pageable) {
        Page<Pedido> dados =  pedidoRepository.findAll(pageable);
        return dados.map(PedidoDTO::new);
    }

}
