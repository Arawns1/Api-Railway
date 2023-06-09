package com.trabalhoFinal.apiEcommerce.services;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trabalhoFinal.apiEcommerce.dto.CategoriaDTO;
import com.trabalhoFinal.apiEcommerce.dto.MessageDTO;
import com.trabalhoFinal.apiEcommerce.dto.ProdutoCatDTO;
import com.trabalhoFinal.apiEcommerce.entities.Categoria;
import com.trabalhoFinal.apiEcommerce.entities.Produto;
import com.trabalhoFinal.apiEcommerce.exceptions.CategoriaNotFoundException;
import com.trabalhoFinal.apiEcommerce.repositories.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;

	public List<Categoria> getAllCategorias() {
		return categoriaRepository.findAll();
	}

	public List<CategoriaDTO> getAllCategoriasDTO() {
		ModelMapper modelMapper = new ModelMapper();

		List<CategoriaDTO> catDto = new ArrayList<>();

		// percorre cada categoria no banco e converte pra dto
		for (Categoria categoria : categoriaRepository.findAll()) {
			CategoriaDTO novaCatDto = modelMapper.map(categoria, CategoriaDTO.class);

			List<ProdutoCatDTO> produCatDto = new ArrayList<>();

			// percorre cada produto dentro da categoria e converte pra dto
			for (Produto produtos : categoria.getProdutos()) {
				ProdutoCatDTO novoProdCat = modelMapper.map(produtos, ProdutoCatDTO.class);
				produCatDto.add(novoProdCat);
			}

			novaCatDto.setProdutos(produCatDto);
			catDto.add(novaCatDto);
		}

		return catDto;
	}

	public Categoria getCategoriaById(Integer id) {
		return categoriaRepository.findById(id).orElseThrow(() -> new CategoriaNotFoundException(id));
	}

	public Categoria saveCategoria(Categoria categoria) {

		return categoriaRepository.save(categoria);
	}

	public Categoria updateCategoria(Categoria categoria, Integer id) {
		return categoriaRepository.save(categoria);
	}

	public MessageDTO delCategoria(Integer id) {
		categoriaRepository.findById(id).orElseThrow(() -> new CategoriaNotFoundException(id));

		categoriaRepository.deleteById(id);
		return new MessageDTO("Categoria excluida com sucesso");

	}
}
