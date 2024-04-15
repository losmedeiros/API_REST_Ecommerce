package br.armando.ecommerce.excecoes;

@SuppressWarnings("serial")
public class ProdutoNotFoundException extends RuntimeException {
	public ProdutoNotFoundException(String message) {
		super(message);

	}
}
