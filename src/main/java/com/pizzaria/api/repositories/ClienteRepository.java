package com.pizzaria.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pizzaria.api.entities.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
