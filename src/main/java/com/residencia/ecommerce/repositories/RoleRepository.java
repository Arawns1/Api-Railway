package com.residencia.ecommerce.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.residencia.ecommerce.entities.Role;
import com.residencia.ecommerce.entities.RoleEnum;

public interface RoleRepository extends JpaRepository<Role, Integer>{
	Optional<Role>findByName(RoleEnum name);
	
}
