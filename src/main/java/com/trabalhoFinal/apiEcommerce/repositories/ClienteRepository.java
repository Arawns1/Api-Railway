package com.trabalhoFinal.apiEcommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trabalhoFinal.apiEcommerce.entities.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer>{
	
	Boolean existsByCpf(String cpf);
	
	Boolean existsByEmail(String email);

}
