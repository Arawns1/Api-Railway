package com.trabalhoFinal.apiEcommerce.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trabalhoFinal.apiEcommerce.entities.Endereco;
import com.trabalhoFinal.apiEcommerce.entities.User;

public interface EnderecoRepository extends JpaRepository<Endereco, Integer>{
	Optional<User> findByCep(String cep);
	
}
