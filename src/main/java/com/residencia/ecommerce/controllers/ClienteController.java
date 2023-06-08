package com.residencia.ecommerce.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.residencia.ecommerce.dto.ClienteDTO;
import com.residencia.ecommerce.entities.Role;
import com.residencia.ecommerce.entities.RoleEnum;
import com.residencia.ecommerce.repositories.ClienteRepository;
import com.residencia.ecommerce.repositories.RoleRepository;
import com.residencia.ecommerce.security.jwt.JwtUtils;
import com.residencia.ecommerce.services.ClienteService;

import jakarta.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/clientes")
public class ClienteController {
	
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	ClienteRepository clienteRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;	
	
	@Autowired
	ClienteService clienteService;
	
	@GetMapping
    public ResponseEntity<List<ClienteDTO>> getAllClientesDTO() {
        return new ResponseEntity<>(clienteService.getAllClientesDTO(),
                HttpStatus.OK);  
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> getClienteById(@PathVariable Integer id) {
        ClienteDTO clienteResponse = clienteService.getClienteDtoById(id);
        if(null == clienteResponse)
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(clienteResponse, HttpStatus.OK);
    }
    
    
    @PostMapping
    public ResponseEntity<?> saveClienteDTO(@Valid @RequestBody ClienteDTO clienteDTO) {
    	
    	
    	if (clienteRepository.existsByUsername(clienteDTO.getUsername())) {
			return ResponseEntity.badRequest().body("Erro: Username já utilizado!");
		}

		if (clienteRepository.existsByEmail(clienteDTO.getEmail())) {
			return ResponseEntity.badRequest().body("Erro: Email já utilizado!");
		}
		
		clienteDTO.setPassword(encoder.encode(clienteDTO.getPassword()));

		Set<String> strRoles = clienteDTO.getStrRoles();
		Set<Role> roles = new HashSet<>();

		if (strRoles == null || strRoles.isEmpty()) {
			Role userRole = roleRepository.findByName(RoleEnum.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Erro: Role não encontrada."));
			roles.add(userRole);
		} else {
			for(String item : strRoles) {
				String role = item.toLowerCase();
				switch (role) {
				case "admin":
					Role adminRole = roleRepository.findByName(RoleEnum.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Erro: Role não encontrada."));
					roles.add(adminRole);
					break;				
				default:
					Role userRole = roleRepository.findByName(RoleEnum.ROLE_USER)
							.orElseThrow(() -> new RuntimeException("Erro: Role não encontrada."));
					roles.add(userRole);
				}
			};
		}
		clienteDTO.setRoles(roles);    	
        return new ResponseEntity<>(clienteService.saveClienteDTO(clienteDTO),HttpStatus.CREATED);
    }
    
    @PutMapping
    public ResponseEntity<?> updateCliente(@RequestBody ClienteDTO clienteDTO) {
    	if(clienteService.getClienteDtoById(clienteDTO.getIdCliente()) != null) {    		
    		    		
    		clienteDTO.setPassword(encoder.encode(clienteDTO.getPassword()));

    		Set<String> strRoles = clienteDTO.getStrRoles();
    		Set<Role> roles = new HashSet<>();

    		if (strRoles == null || strRoles.isEmpty()) {
    			Role userRole = roleRepository.findByName(RoleEnum.ROLE_USER)
    					.orElseThrow(() -> new RuntimeException("Erro: Role não encontrada."));
    			roles.add(userRole);
    		} else {
    			for(String item : strRoles) {
    				var role = item.toLowerCase();
    				switch (role) {
    				case "admin":
    					Role adminRole = roleRepository.findByName(RoleEnum.ROLE_ADMIN)
    							.orElseThrow(() -> new RuntimeException("Erro: Role não encontrada."));
    					roles.add(adminRole);
    					break;				
    				default:
    					Role userRole = roleRepository.findByName(RoleEnum.ROLE_USER)
    							.orElseThrow(() -> new RuntimeException("Erro: Role não encontrada."));
    					roles.add(userRole);
    				}
    			};
    		}
    		clienteDTO.setRoles(roles);    		
            return new ResponseEntity<> (clienteService.updateClienteDTO(clienteDTO),
                    HttpStatus.OK);
        }
        else {
            return new ResponseEntity<> (clienteDTO,
                    HttpStatus.NOT_FOUND);
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delCliente(@PathVariable Integer id) {
        Boolean resp = clienteService.delCliente(id);
        if(resp)
        	return new ResponseEntity<>(resp,HttpStatus.OK);
        else
        	return new ResponseEntity<>(resp,HttpStatus.NOT_MODIFIED);
    }

}


