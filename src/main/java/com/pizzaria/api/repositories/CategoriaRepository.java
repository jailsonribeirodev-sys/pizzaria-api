package com.pizzaria.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pizzaria.api.entities.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}
