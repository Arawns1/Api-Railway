package com.trabalhoFinal.apiEcommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trabalhoFinal.apiEcommerce.entities.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer>{

	Boolean existsByNome(String nome);
	
}
