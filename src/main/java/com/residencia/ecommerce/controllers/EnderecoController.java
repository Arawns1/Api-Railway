package com.residencia.ecommerce.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.residencia.ecommerce.dto.EnderecoDTO;
import com.residencia.ecommerce.services.EnderecoService;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {
	
	@Autowired
	EnderecoService enderecoService;
	
	@GetMapping
    public ResponseEntity<List<EnderecoDTO>> getAllEnderecos() {
        return new ResponseEntity<>(enderecoService.getAllEnderecosDTO(),
                HttpStatus.OK);       
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<EnderecoDTO> getEnderecoDtoById(@PathVariable Integer id) {
        EnderecoDTO enderecoDtoResponse = enderecoService.getEnderecoDtoById(id);
        
        if(null == enderecoDtoResponse)
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(enderecoDtoResponse, HttpStatus.OK);
    }
    
    
    @PostMapping
    public ResponseEntity<EnderecoDTO> saveEnderecoDTO(@RequestBody EnderecoDTO enderecoDTO) {
        return new ResponseEntity<>(enderecoService.saveEnderecoDTO(enderecoDTO),HttpStatus.CREATED);
    }
    
    @PutMapping
    public ResponseEntity<EnderecoDTO> updateEndereco(@RequestBody EnderecoDTO enderecoDTO) {
    	if(enderecoService.getEnderecoDtoById(enderecoDTO.getIdEndereco()) != null) {
            return new ResponseEntity<> (enderecoService.updateEnderecoDTO(enderecoDTO),
                    HttpStatus.OK);
        }
        else {
            return new ResponseEntity<> (enderecoDTO,
                    HttpStatus.NOT_FOUND);
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delEndereco(@PathVariable Integer id) {
        Boolean resp = enderecoService.delEndereco(id);
        if(resp)
        	return new ResponseEntity<>(resp,HttpStatus.OK);
        else
        	return new ResponseEntity<>(resp,HttpStatus.NOT_MODIFIED);
    }

}



