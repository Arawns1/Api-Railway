package com.trabalhoFinal.apiEcommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trabalhoFinal.apiEcommerce.entities.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer>{

	Boolean existsByDescricao(String descricao);
	
}
