package com.trabalhoFinal.apiEcommerce.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trabalhoFinal.apiEcommerce.entities.Role;
import com.trabalhoFinal.apiEcommerce.repositories.RoleRepository;

@Service
public class RoleService {
	@Autowired
	RoleRepository roleRepository;
	
	public Role save(Role role) {
		return roleRepository.save(role);
	}
}
