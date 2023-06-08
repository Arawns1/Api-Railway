package com.residencia.ecommerce.services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.residencia.ecommerce.dto.CategoriaDTO;
import com.residencia.ecommerce.entities.Categoria;
import com.residencia.ecommerce.exceptions.CustomException;
import com.residencia.ecommerce.repositories.CategoriaRepository;

@Service
public class CategoriaService {
	
	@Autowired
	CategoriaRepository categoriaRepository;
	@Autowired
	ModelMapper modelMapper;
	
	public List<CategoriaDTO> getAllCategoriasDTO() {		
		List<Categoria> listaCategoria = categoriaRepository.findAll();
		List<CategoriaDTO> listaCategoriaDTO = listaCategoria.stream().map(x -> new CategoriaDTO(x)).toList();				
		return listaCategoriaDTO;
	}
	
	public CategoriaDTO getCategoriaDtoById(Integer id) {
		Categoria categoria = categoriaRepository.findById(id).orElse(null);
		
		if(categoria==null)
			return null;		
		CategoriaDTO categoriaDTO = modelMapper.map(categoria, CategoriaDTO.class);	
		return categoriaDTO;
		
	}
	
	public CategoriaDTO saveCategoriaDTO(CategoriaDTO categoriaDTO) {
		Categoria categoria = modelMapper.map(categoriaDTO, Categoria.class);
		Categoria saveCategoriaResponse = categoriaRepository.save(categoria);
		if(saveCategoriaResponse == null) {
			throw new CustomException("Erro ao salvar no banco");
		}		
		return modelMapper.map(saveCategoriaResponse, CategoriaDTO.class);		 
	}
	
	public CategoriaDTO updateCategoriaDTO(CategoriaDTO categoriaDTO) {
	
		Categoria categoria = modelMapper.map(categoriaDTO, Categoria.class);
		Categoria saveCategoriaResponse = categoriaRepository.save(categoria);
		return modelMapper.map(saveCategoriaResponse, CategoriaDTO.class);		
	}
	
	   public Boolean delCategoria(Integer id) {
		  if(categoriaRepository.findById(id).orElse(null)!=null) {
			  categoriaRepository.deleteById(id);
			  if(categoriaRepository.findById(id).orElse(null)==null)
				  return true;
		     else
		    	 return false;
		  }
		    else return false;
	    	  
	      }
}
