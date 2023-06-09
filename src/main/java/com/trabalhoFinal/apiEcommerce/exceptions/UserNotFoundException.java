package com.trabalhoFinal.apiEcommerce.exceptions;

public class UserNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public UserNotFoundException(Integer id) {
		super("Não foi encontrado Usuário com o id = " + id);
	}
	
	public UserNotFoundException(String mensagem) {
		super(mensagem);
	}
}
