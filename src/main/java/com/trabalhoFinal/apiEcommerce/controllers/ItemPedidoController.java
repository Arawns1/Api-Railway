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

import com.trabalhoFinal.apiEcommerce.dto.MessageDTO;
import com.trabalhoFinal.apiEcommerce.entities.ItemPedido;
import com.trabalhoFinal.apiEcommerce.services.ItemPedidoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/itemPedidos")
public class ItemPedidoController {

	@Autowired
	ItemPedidoService itemPedidoService;
	
	@GetMapping 
	public ResponseEntity<List<ItemPedido>> getAllItemPedidos() {
		return new ResponseEntity<>(itemPedidoService.getAllItemPedidos(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ItemPedido> getItemPedidoById(@PathVariable Integer id) {
		return new ResponseEntity<>(itemPedidoService.getItemPedidoById(id), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<?> saveItemPedido(@RequestBody @Valid ItemPedido itemPedido) {
		Boolean retorno = itemPedidoService.saveItemPedido(itemPedido);
		
		if(retorno)
			return new ResponseEntity<>(new MessageDTO("ItemPedido cadastrado com sucesso"), HttpStatus.CREATED);
		else
			return new ResponseEntity<>(new MessageDTO("Quantidade insuficiente no estoque"), HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<ItemPedido> updateItemPedido(@RequestBody @Valid ItemPedido itemPedido, Integer id) {
		return new ResponseEntity<>(itemPedidoService.updateItemPedido(itemPedido, id), HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<MessageDTO> delItemPedido(@PathVariable Integer id) {
		return new ResponseEntity<>(itemPedidoService.delItemPedido(id), HttpStatus.OK);
		
	}
}
