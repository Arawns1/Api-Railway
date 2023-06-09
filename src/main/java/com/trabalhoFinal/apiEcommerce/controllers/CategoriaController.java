package com.trabalhoFinal.apiEcommerce.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trabalhoFinal.apiEcommerce.dto.CategoriaDTO;
import com.trabalhoFinal.apiEcommerce.dto.MessageDTO;
import com.trabalhoFinal.apiEcommerce.dto.MessageResponseDTO;
import com.trabalhoFinal.apiEcommerce.entities.Categoria;
import com.trabalhoFinal.apiEcommerce.repositories.CategoriaRepository;
import com.trabalhoFinal.apiEcommerce.services.CategoriaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

	@Autowired
	CategoriaService categoriaService;

	@Autowired
	CategoriaRepository categoriaRepository;

	@GetMapping
	public ResponseEntity<List<Categoria>> getAllCategorias() {
		return new ResponseEntity<>(categoriaService.getAllCategorias(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Categoria> getCategoriaById(@PathVariable Integer id) {
		return new ResponseEntity<>(categoriaService.getCategoriaById(id), HttpStatus.OK);
	}

	// DTO
	@GetMapping("/dto")
	public ResponseEntity<List<CategoriaDTO>> getAllCategoriasDTO() {
		return new ResponseEntity<>(categoriaService.getAllCategoriasDTO(), HttpStatus.OK);
	}

	@PreAuthorize("hasAnyRole('ADMIN', 'MODERATOR')")
	@PostMapping
	public ResponseEntity<?> saveCategoria(@RequestBody @Valid Categoria categoria) {
		if (categoriaRepository.existsByNome(categoria.getNome()))
			return ResponseEntity.badRequest().body(new MessageResponseDTO("Erro: Nome de categoria já utilizado!"));

		return new ResponseEntity<>(categoriaService.saveCategoria(categoria), HttpStatus.CREATED);
	}

	@PreAuthorize("hasAnyRole('ADMIN', 'MODERATOR')")
	@PutMapping
	public ResponseEntity<?> updateCategoria(@RequestBody @Valid Categoria categoria, Integer id) {

		return new ResponseEntity<>(categoriaService.updateCategoria(categoria, id), HttpStatus.OK);
	}

	@PreAuthorize("hasAnyRole('ADMIN', 'MODERATOR')")
	@DeleteMapping("/{id}")
	public ResponseEntity<MessageDTO> delCategoria(@PathVariable Integer id) {
		return new ResponseEntity<>(categoriaService.delCategoria(id), HttpStatus.OK);
	}
}
