package com.residencia.ecommerce.services;

import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.residencia.ecommerce.dto.ProdutoDTO;
import com.residencia.ecommerce.entities.Produto;
import com.residencia.ecommerce.exceptions.CustomException;
import com.residencia.ecommerce.repositories.ProdutoRepository;

@Service
public class ProdutoService {
	
	@Autowired
	ProdutoRepository produtoRepository;
	
	@Autowired
	ModelMapper modelMapper;
	
	public List<ProdutoDTO> getAllProdutosDTO() {
		List<Produto> produtos = produtoRepository.findAll();
		List<ProdutoDTO> listaProdutoDTO = produtos.stream().map(x -> new ProdutoDTO(x)).toList();						
		return listaProdutoDTO;
	}
	
	public ProdutoDTO getProdutoDtoById(Integer id) {
		Produto produto = produtoRepository.findById(id).orElse(null);		
		if(produto==null)
			return null;		
		ProdutoDTO produtoDTO = modelMapper.map(produto, ProdutoDTO.class);		
		return produtoDTO;		
	}
	
	public ProdutoDTO saveProdutoDTO(String produtoDTO, MultipartFile file) {	
		
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			ProdutoDTO prodDTO = objectMapper.readValue(produtoDTO, ProdutoDTO.class);
		
			Produto produto = modelMapper.map(prodDTO, Produto.class);		
			try {
				produto.setImagem(file.getBytes());
			}catch(java.io.IOException e) {
				throw new CustomException("Ocorreu um erro ao tentar converter a imagem");			 
			}
			List<Produto> listaProduto = produtoRepository.findAll();
			for(Produto prod:listaProduto){
				if(prod.getNome().toLowerCase().equals(produto.getNome().toLowerCase())) {
					throw new CustomException("Já existe um produto com este nome");
				}
				if(prod.getDescricao().toLowerCase().equals(produto.getDescricao().toLowerCase())) {
					throw new CustomException("Já existe um produto com esta descrição");
				}				
			}
			produto.setDataCadastro(new Date());
			Produto saveProdResponse =  produtoRepository.save(produto);
		
			if(saveProdResponse == null) {
				throw new CustomException("Erro ao salvar no banco");
			}		
			return modelMapper.map(saveProdResponse, ProdutoDTO.class);	
		
		} catch (JsonMappingException e) {
			System.out.println(e.toString()); 
			throw new CustomException("Erro ao Converter O Json para ProtudoDTO.class");
			
		} catch (JsonProcessingException e) {			
			throw new CustomException("Erro ao Converter O Json para ProtudoDTO.class");
		}			 
	}
	
public ProdutoDTO updateProdutoDTO(String produtoDTO, MultipartFile file) {	
		
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			ProdutoDTO prodDTO = objectMapper.readValue(produtoDTO, ProdutoDTO.class);
		
			Produto produto = modelMapper.map(prodDTO, Produto.class);	
			
			Produto response = produtoRepository.findById(produto.getIdProduto()).orElse(null);
			if(response == null)
				return null;
			
			try {
				produto.setImagem(file.getBytes());
			}catch(java.io.IOException e) {
				throw new CustomException("Ocorreu um erro ao tentar converter a imagem");			 
			}
			List<Produto> listaProduto = produtoRepository.findAll();
			for(Produto prod:listaProduto){
				if(prod.getNome().toLowerCase().equals(produto.getNome().toLowerCase())) {
					throw new CustomException("Já existe um produto com este nome");
				}
				if(prod.getDescricao().toLowerCase().equals(produto.getDescricao().toLowerCase())) {
					throw new CustomException("Já existe um produto com esta descrição");
				}				
			}
			produto.setDataCadastro(new Date());
			Produto saveProdResponse =  produtoRepository.save(produto);
		
			if(saveProdResponse == null) {
				throw new CustomException("Erro ao salvar no banco");
			}		
			return modelMapper.map(saveProdResponse, ProdutoDTO.class);	
		
		} catch (JsonMappingException e) {
			System.out.println(e.toString()); 
			throw new CustomException("Erro ao Converter O Json para ProtudoDTO.class");
			
		} catch (JsonProcessingException e) {			
			throw new CustomException("Erro ao Converter O Json para ProtudoDTO.class");
		}			 
	}
	
	   public Boolean delProduto(Integer id) {
		  if(produtoRepository.findById(id).orElse(null)!=null) {
			  produtoRepository.deleteById(id);
			  if(produtoRepository.findById(id).orElse(null)==null)
				  return true;
		     else
		    	 return false;
		  }
		    else return false;
	    	  
	      }
	   public byte[] getProdutoImg(Integer id) {
		   Produto produto = produtoRepository.findById(id).orElse(null);
		   if(produto==null) {
			   throw new CustomException("Produto de id: "+id+" não encontrado");
		   }
		   return produto.getImagem();
	   }
}
