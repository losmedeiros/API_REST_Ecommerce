package br.armando.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.armando.ecommerce.entity.Produto;
import br.armando.ecommerce.repository.ProdutoRepository;

@Service
public class ProdutoService {
	@Autowired
	private final ProdutoRepository produtoRepository;

	public ProdutoService(ProdutoRepository produtoRepository) {
		this.produtoRepository = produtoRepository;
	}

	public List<Produto> listarProdutos() {
		return produtoRepository.findAll();
	}

	public Produto salvarProduto(Produto produto) {
		return produtoRepository.save(produto); // Salva o produto no banco de dados
	}

	public Optional<Produto> deletarProduto(Long id) {
		Optional<Produto> produtoDeletado = produtoRepository.findById(id);
		produtoRepository.deleteById(id); // Deleta o produto do banco de dados pelo ID
		return produtoDeletado;
	}

	// Outros metodos relacionados a produtos

}
