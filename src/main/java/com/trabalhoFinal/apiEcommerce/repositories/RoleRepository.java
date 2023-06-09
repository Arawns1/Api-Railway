package com.trabalhoFinal.apiEcommerce.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trabalhoFinal.apiEcommerce.entities.Role;
import com.trabalhoFinal.apiEcommerce.entities.RoleEnum;


public interface RoleRepository extends JpaRepository<Role, Integer> {
	Optional<Role> findByName(RoleEnum name);
}