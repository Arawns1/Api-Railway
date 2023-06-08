package com.residencia.ecommerce.services;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.residencia.ecommerce.dto.PedidoDTO;
import com.residencia.ecommerce.entities.Pedido;
import com.residencia.ecommerce.exceptions.CustomException;
import com.residencia.ecommerce.repositories.PedidoRepository;

@Service
public class PedidoService {
	
	@Autowired
	PedidoRepository pedidoRepository;
	@Autowired
	ModelMapper modelMapper;
	
	public List<PedidoDTO> getAllPedidosDTO() {		
		List<Pedido> listaPedido = pedidoRepository.findAll();
		List<PedidoDTO> listaPedidoDTO = listaPedido.stream().map(x -> new PedidoDTO(x)).toList();				
		return listaPedidoDTO;
	}
	
	public PedidoDTO getPedidoDtoById(Integer id) {
		Pedido pedido = pedidoRepository.findById(id).orElse(null);		
		if(pedido==null)
			return null;			
		PedidoDTO pedidoDTO = modelMapper.map(pedido, PedidoDTO.class);		
		return pedidoDTO;		
	}
	
	public PedidoDTO savePedidoDTO(PedidoDTO pedidoDTO) {
		Pedido pedido = modelMapper.map(pedidoDTO, Pedido.class);
		pedido.setValorTotal(new BigDecimal("0.0"));
		pedido.setDataPedido(new Date());
		Pedido savePedidoResponse = pedidoRepository.save(pedido);
		if(savePedidoResponse == null) {
			throw new CustomException("Erro ao salvar no banco");
		}
		
		return modelMapper.map(savePedidoResponse, PedidoDTO.class);		 
	}
	
	public PedidoDTO updatePedidoDTO(PedidoDTO pedidoDTO) {
	
		Pedido pedido = modelMapper.map(pedidoDTO, Pedido.class);
		Pedido savePedidoResponse = pedidoRepository.save(pedido);
		return modelMapper.map(savePedidoResponse, PedidoDTO.class);		
	}
	
	   public Boolean delPedido(Integer id) {
		  if(pedidoRepository.findById(id).orElse(null)!=null) {
			  pedidoRepository.deleteById(id);
			  if(pedidoRepository.findById(id).orElse(null)==null)
				  return true;
		     else
		    	 return false;
		  }
		    else return false;
	    	  
	      }
}
