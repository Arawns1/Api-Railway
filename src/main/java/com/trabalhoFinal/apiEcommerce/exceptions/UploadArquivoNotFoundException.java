package com.trabalhoFinal.apiEcommerce.exceptions;

public class UploadArquivoNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public UploadArquivoNotFoundException(Integer id) {
		super("Não foi encontrado Arquivo com o id = " + id);
	}
}
