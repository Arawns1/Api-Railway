package com.residencia.ecommerce.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.residencia.ecommerce.dto.RoleDTO;
import com.residencia.ecommerce.services.RoleService;

import jakarta.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/roles")
public class RoleController {
	@Autowired
	RoleService roleService;
	
	@PostMapping
	public ResponseEntity<RoleDTO> save(@Valid @RequestBody RoleDTO roleDTO) {
		RoleDTO newRole = roleService.saveRoleDTO(roleDTO);
		if(newRole != null)
			return new ResponseEntity<>(newRole, HttpStatus.CREATED);
		else
			return new ResponseEntity<>(newRole, HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping
    public ResponseEntity<List<RoleDTO>> getAllRoles() {
        return new ResponseEntity<>(roleService.getAllRolesDTO(),
                HttpStatus.OK);       
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<RoleDTO> getRoleDtoById(@PathVariable Integer id) {
        RoleDTO roleDtoResponse = roleService.getRoleDtoById(id);
        
        if(null == roleDtoResponse)
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(roleDtoResponse, HttpStatus.OK);
    }    
   
    
    @PutMapping
    public ResponseEntity<RoleDTO> updateRole(@RequestBody RoleDTO roleDTO) {
    	if(roleService.getRoleDtoById(roleDTO.getId()) != null) {
            return new ResponseEntity<> (roleService.updateRoleDTO(roleDTO),
                    HttpStatus.OK);
        }
        else {
            return new ResponseEntity<> (roleDTO,
                    HttpStatus.NOT_FOUND);
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delRole(@PathVariable Integer id) {
        Boolean resp = roleService.delRole(id);
        if(resp)
        	return new ResponseEntity<>(resp,HttpStatus.OK);
        else
        	return new ResponseEntity<>(resp,HttpStatus.NOT_MODIFIED);
    }
	
	

}