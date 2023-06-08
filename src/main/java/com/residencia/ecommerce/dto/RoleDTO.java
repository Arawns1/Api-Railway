package com.residencia.ecommerce.dto;

import com.residencia.ecommerce.entities.Role;
import com.residencia.ecommerce.entities.RoleEnum;

public class RoleDTO {
	
	private Integer id;

	
	private RoleEnum name;

	public RoleDTO(Integer id, RoleEnum name) {
		super();
		this.id = id;
		this.name = name;
	}

	public RoleDTO() {
		super();
	}
	

	public RoleDTO(Role role) {
		super();
		this.id = role.getId();
		this.name = role.getName();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public RoleEnum getName() {
		return name;
	}

	public void setName(RoleEnum name) {
		this.name = name;
	}

}
