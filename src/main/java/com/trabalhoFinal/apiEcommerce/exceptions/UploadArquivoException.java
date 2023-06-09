package com.trabalhoFinal.apiEcommerce.exceptions;

public class UploadArquivoException extends RuntimeException{
	private static final long serialVersionUID = 1L;

public UploadArquivoException() {
		
	}

	public UploadArquivoException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public UploadArquivoException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public UploadArquivoException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public UploadArquivoException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
}
