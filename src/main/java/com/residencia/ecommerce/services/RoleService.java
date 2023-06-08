package com.residencia.ecommerce.services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.residencia.ecommerce.dto.RoleDTO;
import com.residencia.ecommerce.entities.Role;
import com.residencia.ecommerce.exceptions.CustomException;
import com.residencia.ecommerce.repositories.RoleRepository;

@Service
public class RoleService {
	
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	ModelMapper modelMapper;
	
	public List<RoleDTO> getAllRolesDTO() {		
		List<Role> listaRole = roleRepository.findAll();
		List<RoleDTO> listaRoleDTO = listaRole.stream().map(x -> new RoleDTO(x)).toList();				
		return listaRoleDTO;
	}
	
	public RoleDTO getRoleDtoById(Integer id) {
		Role role = roleRepository.findById(id).orElse(null);
		
		if(role==null)
			return null;		
		RoleDTO roleDTO = modelMapper.map(role, RoleDTO.class);	
		return roleDTO;
		
	}
	
	public RoleDTO saveRoleDTO(RoleDTO roleDTO) {
		Role role = modelMapper.map(roleDTO, Role.class);		
		role.setName(roleDTO.getName());
		Role saveRoleResponse = roleRepository.save(role);
		if(saveRoleResponse == null) {
			throw new CustomException("Erro ao salvar no banco");
		}		
		return modelMapper.map(saveRoleResponse, RoleDTO.class);		 
	}
	
	public RoleDTO updateRoleDTO(RoleDTO roleDTO) {
	
		Role role = modelMapper.map(roleDTO, Role.class);
		Role saveRoleResponse = roleRepository.save(role);
		return modelMapper.map(saveRoleResponse, RoleDTO.class);		
	}
	
	   public Boolean delRole(Integer id) {
		  if(roleRepository.findById(id).orElse(null)!=null) {
			  roleRepository.deleteById(id);
			  if(roleRepository.findById(id).orElse(null)==null)
				  return true;
		     else
		    	 return false;
		  }
		    else return false;
	    	  
	      }
}
