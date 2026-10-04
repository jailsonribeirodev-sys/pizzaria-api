package com.pizzaria.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pizzaria.api.entities.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

}
