package com.residencia.ecommerce.controllers;

import java.util.List;

import org.modelmapper.ModelMapper;
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

import com.residencia.ecommerce.dto.ItemPedidoDTO;
import com.residencia.ecommerce.services.ItemPedidoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/itempedidos")
public class ItemPedidoController {
	
	@Autowired
	ItemPedidoService itemPedidoService;
	
	@Autowired
	ModelMapper modelMapper;
	
	@GetMapping
    public ResponseEntity<List<ItemPedidoDTO>> getAllItemPedidosDTO() {
        return new ResponseEntity<>(itemPedidoService.getAllItemPedidosDTO(),
                HttpStatus.OK);
        //ResponseEntity manipula o retorno
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ItemPedidoDTO> getItemPedidoDtoById(@PathVariable Integer id) {
        ItemPedidoDTO itemPedidoDTOResponse = itemPedidoService.getItemPedidoDtoById(id);
        if(null == itemPedidoDTOResponse)
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(itemPedidoDTOResponse, HttpStatus.OK);
    }
    
    
    @PostMapping
    public ResponseEntity<ItemPedidoDTO> saveItemPedidoDTO(@Valid @RequestBody ItemPedidoDTO itemPedidoDTO) {
       
    	return new ResponseEntity<>(itemPedidoService.saveItemPedidoDTO(itemPedidoDTO),HttpStatus.CREATED);
    }
    
    @PutMapping
    public ResponseEntity<ItemPedidoDTO> updateItemPedidoDTO(@Valid @RequestBody ItemPedidoDTO itemPedidoDTO) {
    	if(itemPedidoService.getItemPedidoDtoById(itemPedidoDTO.getIdItemPedido()) != null) {
            return new ResponseEntity<> (itemPedidoService.updateItemPedidoDTO(itemPedidoDTO),
                    HttpStatus.OK);
        }
        else {
            return new ResponseEntity<> (itemPedidoDTO,
                    HttpStatus.NOT_FOUND);
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delItemPedido(@PathVariable Integer id) {
        Boolean resp = itemPedidoService.delItemPedido(id);
        if(resp)
        	return new ResponseEntity<>(resp,HttpStatus.OK);
        else
        	return new ResponseEntity<>(resp,HttpStatus.NOT_MODIFIED);
    }

}



