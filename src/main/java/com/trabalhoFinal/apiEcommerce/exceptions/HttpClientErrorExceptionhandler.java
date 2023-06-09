package com.trabalhoFinal.apiEcommerce.exceptions;

public class HttpClientErrorExceptionhandler extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public HttpClientErrorExceptionhandler(String cep) {
		super("Não foi encontrado Endereço com o cep = " + cep);
	}
	
}
