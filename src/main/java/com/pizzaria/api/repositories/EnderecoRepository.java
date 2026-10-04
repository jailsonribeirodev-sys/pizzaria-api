package com.pizzaria.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pizzaria.api.entities.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

}
