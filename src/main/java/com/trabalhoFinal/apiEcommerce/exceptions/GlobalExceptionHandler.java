package com.trabalhoFinal.apiEcommerce.exceptions;

import java.net.URI;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(UserNotFoundException.class)
	ProblemDetail handleBookmarkNotFoundException(UserNotFoundException e) {
		ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
		problemDetail.setTitle("Usuário Não Encontrado");
		problemDetail.setType(URI.create("https://api.grupo3trabalho.com/errors/not-found"));
		return problemDetail;

	}

	@ExceptionHandler(UploadArquivoNotFoundException.class)
	ProblemDetail handleBookmarkNotFoundException(UploadArquivoNotFoundException e) {
		ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
		problemDetail.setTitle("Arquivo Não Encontrado");
		problemDetail.setType(URI.create("https://api.grupo3trabalho.com/errors/not-found"));
		return problemDetail;

	}
	
	
	@ExceptionHandler(ClienteNotFoundException.class)
	ProblemDetail handleBookmarkNotFoundException(ClienteNotFoundException e) {
		ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
		problemDetail.setTitle("Cliente Não Encontrado");
		problemDetail.setType(URI.create("https://api.grupo3trabalho.com/errors/not-found"));
		return problemDetail;

	}

	@ExceptionHandler(CategoriaNotFoundException.class)
	ProblemDetail handleBookmarkNotFoundException(CategoriaNotFoundException e) {
		ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
		problemDetail.setTitle("Categoria Não Encontrada");
		problemDetail.setType(URI.create("https://api.grupo3trabalho.com/errors/not-found"));
		return problemDetail;

	}

	@ExceptionHandler(EnderecoNotFoundException.class)
	ProblemDetail handleBookmarkNotFoundException(EnderecoNotFoundException e) {
		ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
		problemDetail.setTitle("Endereço Não Encontrado");
		problemDetail.setType(URI.create("https://api.grupo3trabalho.com/errors/not-found"));
		return problemDetail;

	}

	@ExceptionHandler(ItemPedidoNotFoundException.class)
	ProblemDetail handleBookmarkNotFoundException(ItemPedidoNotFoundException e) {
		ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
		problemDetail.setTitle("ItemPedido Não Encontrado");
		problemDetail.setType(URI.create("https://api.grupo3trabalho.com/errors/not-found"));
		return problemDetail;

	}

	@ExceptionHandler(PedidoNotFoundException.class)
	ProblemDetail handleBookmarkNotFoundException(PedidoNotFoundException e) {
		ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
		problemDetail.setTitle("Pedido Não Encontrado");
		problemDetail.setType(URI.create("https://api.grupo3trabalho.com/errors/not-found"));
		return problemDetail;

	}

	@ExceptionHandler(ProdutoNotFoundException.class)
	ProblemDetail handleBookmarkNotFoundException(ProdutoNotFoundException e) {
		ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
		problemDetail.setTitle("Produto Não Encontrado");
		problemDetail.setType(URI.create("https://api.grupo3trabalho.com/errors/not-found"));
		return problemDetail;

	}
	
	@ExceptionHandler(HttpClientErrorExceptionhandler.class)
	ProblemDetail handleBookmarkNotFoundException(HttpClientErrorExceptionhandler e) {
		ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
		problemDetail.setTitle("Endereço Não Encontrado");
		problemDetail.setType(URI.create("https://api.grupo3trabalho.com/errors/not-found"));
		return problemDetail;

	}
	

	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, @Nullable Object body, HttpHeaders headers,
			HttpStatusCode statusCode, WebRequest request) {
		ResponseEntity<Object> response = super.handleExceptionInternal(ex, body, headers, statusCode, request);

		if (response.getBody() instanceof ProblemDetail problemDetailBody) {
			problemDetailBody.setProperty("message", ex.getMessage());
			if (ex instanceof MethodArgumentNotValidException subEx) {
				 BindingResult result = subEx.getBindingResult();
				problemDetailBody.setTitle("Erro na requisição");
				problemDetailBody.setDetail("Ocorreu um erro ao processar a requisição");
				problemDetailBody.setProperty("message", "Verifique os erros abaixo");

				for (int i = 0; i < result.getAllErrors().size(); i++) {
				
					problemDetailBody.setProperty("error" + (i+1), result.getAllErrors().get(i).getDefaultMessage() );
					
				}
			}
		}
		return response;
	}

}
