package com.pizzaria.api.services;

import com.pizzaria.api.dto_response.ItemPedidoDTO;
import com.pizzaria.api.entities.ItemPedido;
import com.pizzaria.api.entities.pk.ItemPedidoPk;
import com.pizzaria.api.exceptions.RecursoNaoEncontradoException;
import com.pizzaria.api.repositories.ItemPedidoRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;

@Service
public class ItemPedidoService {
    private final ItemPedidoRepository itemPedidoRepository;

    public ItemPedidoService(ItemPedidoRepository itemPedidoRepository) {
        this.itemPedidoRepository = itemPedidoRepository;
    }

    public ItemPedidoDTO findById(Long pedidoId, Long produtoId) {
        ItemPedidoPk id = new ItemPedidoPk(produtoId, pedidoId);
        return itemPedidoRepository.findById(id)
                .map(ItemPedidoDTO::new)
                .orElseThrow(() -> new RecursoNaoEncontradoException("ItemPedido não encontrado para pedido " + pedidoId + " e produto " + produtoId));
    }

    public Page<ItemPedidoDTO> findAll(@PageableDefault(size = 10) Pageable pageable) {
        Page<ItemPedido> dados = itemPedidoRepository.findAll(pageable);
        return dados.map(ItemPedidoDTO::new);
    }

    @Transactional
    public ItemPedidoDTO updateItemPedido(Long pedidoId, Long produtoId, Integer quantidade, String observacao) {
        ItemPedidoPk id = new ItemPedidoPk(produtoId, pedidoId);
        ItemPedido item = itemPedidoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("ItemPedido não encontrado para pedido " + pedidoId + " e produto " + produtoId));
        if (quantidade != null && quantidade > 0) {
            item.setQuantidade(quantidade);
        }
        if (observacao != null) {
            item.setObservacao(observacao);
        }
        item = itemPedidoRepository.save(item);
        return new ItemPedidoDTO(item);
    }
}
