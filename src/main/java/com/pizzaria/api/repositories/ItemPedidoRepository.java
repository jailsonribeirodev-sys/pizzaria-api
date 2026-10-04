package com.pizzaria.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pizzaria.api.entities.ItemPedido;
import com.pizzaria.api.entities.pk.ItemPedidoPk;


public interface ItemPedidoRepository extends JpaRepository<ItemPedido, ItemPedidoPk> {
}
