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

import com.residencia.ecommerce.dto.PedidoDTO;
import com.residencia.ecommerce.services.PedidoService;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
	
	@Autowired
	PedidoService pedidoService;
	
	@GetMapping
    public ResponseEntity<List<PedidoDTO>> getAllPedidos() {
        return new ResponseEntity<>(pedidoService.getAllPedidosDTO(),
                HttpStatus.OK);
        //ResponseEntity manipula o retorno
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<PedidoDTO> getPedidoDtoById(@PathVariable Integer id) {
        PedidoDTO pedidoDtoResponse = pedidoService.getPedidoDtoById(id);
        if(null == pedidoDtoResponse)
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(pedidoDtoResponse, HttpStatus.OK);
    }
    
    
    @PostMapping
    public ResponseEntity<PedidoDTO> savePedidoDTO(@RequestBody PedidoDTO pedidoDTO) {
        return new ResponseEntity<>(pedidoService.savePedidoDTO(pedidoDTO),HttpStatus.CREATED);
    }
    
    @PutMapping
    public ResponseEntity<PedidoDTO> updatePedidoDTO(@RequestBody PedidoDTO pedidoDTO) {
    	if(pedidoService.getPedidoDtoById(pedidoDTO.getIdPedido()) != null) {
            return new ResponseEntity<> (pedidoService.updatePedidoDTO(pedidoDTO),
                    HttpStatus.OK);
        }
        else {
            return new ResponseEntity<> (pedidoDTO,
                    HttpStatus.NOT_FOUND);
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delPedido(@PathVariable Integer id) {
        Boolean resp = pedidoService.delPedido(id);
        if(resp)
        	return new ResponseEntity<>(resp,HttpStatus.OK);
        else
        	return new ResponseEntity<>(resp,HttpStatus.NOT_MODIFIED);
    }

}



