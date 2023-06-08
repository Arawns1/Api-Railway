package com.residencia.ecommerce.services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.residencia.ecommerce.dto.ClienteDTO;
import com.residencia.ecommerce.entities.Cliente;
import com.residencia.ecommerce.exceptions.CustomException;
import com.residencia.ecommerce.repositories.ClienteRepository;

@Service
public class ClienteService {
	
	@Autowired
	ClienteRepository clienteRepository;
	@Autowired
	ModelMapper modelMapper;
	
	public List<ClienteDTO> getAllClientesDTO() {		
		List<Cliente> listaCliente = clienteRepository.findAll();
		List<ClienteDTO> listaClienteDTO = listaCliente.stream().map(x -> new ClienteDTO(x)).toList();				
		return listaClienteDTO;
	}
	
	public ClienteDTO getClienteDtoById(Integer id) {
		Cliente cliente = clienteRepository.findById(id).orElse(null);
		
		if(cliente==null)
			return null;		
		ClienteDTO clienteDTO = modelMapper.map(cliente, ClienteDTO.class);	
		return clienteDTO;
		
	}
	
	public ClienteDTO saveClienteDTO(ClienteDTO clienteDTO) {
		Cliente cliente = modelMapper.map(clienteDTO, Cliente.class);
		Cliente saveClienteResponse = clienteRepository.save(cliente);
		if(saveClienteResponse == null) {
			throw new CustomException("Erro ao salvar no banco");
		}		
		return modelMapper.map(saveClienteResponse, ClienteDTO.class);		 
	}
	
	public ClienteDTO updateClienteDTO(ClienteDTO clienteDTO) {
	
		Cliente cliente = modelMapper.map(clienteDTO, Cliente.class);
		Cliente saveClienteResponse = clienteRepository.save(cliente);
		return modelMapper.map(saveClienteResponse, ClienteDTO.class);		
	}
	
	   public Boolean delCliente(Integer id) {
		  if(clienteRepository.findById(id).orElse(null)!=null) {
			  clienteRepository.deleteById(id);
			  if(clienteRepository.findById(id).orElse(null)==null)
				  return true;
		     else
		    	 return false;
		  }
		    else return false;
	    	  
	      }
}
