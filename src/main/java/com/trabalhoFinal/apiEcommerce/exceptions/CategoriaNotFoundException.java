package com.trabalhoFinal.apiEcommerce.exceptions;

public class CategoriaNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CategoriaNotFoundException(Integer id) {
		super("Não foi encontrada Categoria com o id = " + id);
	}
}
