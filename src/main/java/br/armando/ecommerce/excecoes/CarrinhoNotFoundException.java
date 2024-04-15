package br.armando.ecommerce.excecoes;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(HttpStatus.NOT_FOUND)
public class CarrinhoNotFoundException extends RuntimeException {
	public CarrinhoNotFoundException(String message) {
		super(message);
	}
}
