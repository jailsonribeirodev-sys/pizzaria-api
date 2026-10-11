package com.pizzaria.api.services;

import com.pizzaria.api.repositories.ItemPedidoRepository;
import org.springframework.stereotype.Service;

@Service
public class ItemPedidoService {
    private final ItemPedidoRepository itemPedidoRepository;

    public ItemPedidoService(ItemPedidoRepository itemPedidoRepository) {
        this.itemPedidoRepository = itemPedidoRepository;
    }
}
