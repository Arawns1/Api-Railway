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

import com.residencia.ecommerce.dto.CategoriaDTO;
import com.residencia.ecommerce.services.CategoriaService;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {
	
	@Autowired
	CategoriaService categoriaService;
	
	@GetMapping
    public ResponseEntity<List<CategoriaDTO>> getAllCategoriasDTO() {
        return new ResponseEntity<>(categoriaService.getAllCategoriasDTO(),
                HttpStatus.OK);
        
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDTO> getCategoriaDtoById(@PathVariable Integer id) {
        CategoriaDTO categoriaDTOResponse = categoriaService.getCategoriaDtoById(id);
        if(null == categoriaDTOResponse)
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(categoriaDTOResponse, HttpStatus.OK);
    }
    
    
    @PostMapping
    public ResponseEntity<CategoriaDTO> saveCategoriaDTO(@RequestBody CategoriaDTO categoriaDTO) {
        return new ResponseEntity<>(categoriaService.saveCategoriaDTO(categoriaDTO),HttpStatus.CREATED);
    }
    
    @PutMapping
    public ResponseEntity<CategoriaDTO> updateCategoriaDTO(@RequestBody CategoriaDTO categoriaDTO) {
    	if(categoriaService.getCategoriaDtoById(categoriaDTO.getIdCategoria()) != null) {
            return new ResponseEntity<> (categoriaService.updateCategoriaDTO(categoriaDTO),
                    HttpStatus.OK);
        }
        else {
            return new ResponseEntity<> (categoriaDTO,
                    HttpStatus.NOT_FOUND);
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delCategoria(@PathVariable Integer id) {
        Boolean resp = categoriaService.delCategoria(id);
        if(resp)
        	return new ResponseEntity<>(resp,HttpStatus.OK);
        else
        	return new ResponseEntity<>(resp,HttpStatus.NOT_MODIFIED);
    }

}



