package br.armando.ecommerce.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.armando.ecommerce.entity.Produto;
import br.armando.ecommerce.service.ProdutoService;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

	@Autowired
	private final ProdutoService produtoService;

	public ProdutoController(ProdutoService produtoService) {
		this.produtoService = produtoService;
	}

	@GetMapping
	public ResponseEntity<List<Produto>> listarProdutos() {
		List<Produto> produtos = produtoService.listarProdutos();
		return ResponseEntity.ok(produtos);
	}

	@PostMapping("/adicionar")
	public ResponseEntity<Produto> adicionarProduto(@RequestBody Produto produto) {
		Produto novoProduto = produtoService.salvarProduto(produto);
		return ResponseEntity.ok(novoProduto);
	}

	@DeleteMapping("/deletar/{id}")
	public ResponseEntity<Optional<Produto>> deletarProduto(@PathVariable Long id) {
		Optional<Produto> produtoDeletado = produtoService.deletarProduto(id);
		return ResponseEntity.ok(produtoDeletado);
	}

}
