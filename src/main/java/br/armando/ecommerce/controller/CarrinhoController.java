package br.armando.ecommerce.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.armando.ecommerce.entity.Carrinho;
import br.armando.ecommerce.entity.Produto;
import br.armando.ecommerce.excecoes.CarrinhoNotFoundException;
import br.armando.ecommerce.excecoes.ProdutoNotFoundException;
import br.armando.ecommerce.repository.CarrinhoRepository;
import br.armando.ecommerce.repository.ProdutoRepository;
import br.armando.ecommerce.service.CarrinhoService;

@RestController
@RequestMapping("/api/carrinho")
public class CarrinhoController {

	@Autowired
	private final CarrinhoService carrinhoService;
	@Autowired
	private final CarrinhoRepository carrinhoRepository;
	@Autowired
	private final ProdutoRepository produtoRepository;

	public CarrinhoController(CarrinhoService carrinhoService, CarrinhoRepository carrinhoRepository,
			ProdutoRepository produtoRepository) {
		this.carrinhoService = carrinhoService;
		this.carrinhoRepository = carrinhoRepository;
		this.produtoRepository = produtoRepository;

	}

	@GetMapping("/{carrinhoId}")
	public ResponseEntity<List<Produto>> listarProdutosDoCarrinho(@PathVariable Long carrinhoId) {
		try {
			List<Produto> produtos = carrinhoService.listarProdutosDoCarrinho(carrinhoId);
			return ResponseEntity.ok(produtos);
		} catch (CarrinhoNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping("/{carrinhoId}/adicionarProduto/{produtoId}")
	public ResponseEntity<Produto> adicionarProdutoAoCarrinho(@PathVariable Long carrinhoId,
			@PathVariable Long produtoId) {
		Optional<Carrinho> carrinhoOptional = carrinhoRepository.findById(carrinhoId);
		Optional<Produto> produtoOptional = produtoRepository.findById(produtoId);

		if (carrinhoOptional.isPresent() && produtoOptional.isPresent()) {
			Carrinho carrinho = carrinhoOptional.get();
			Produto produto = produtoOptional.get();
			carrinhoService.adicionarProdutoAoCarrinho(carrinho, produto);
			return ResponseEntity.ok(produto);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{carrinhoId}/removerProduto/{produtoId}")
	public ResponseEntity<Produto> removerProdutoDoCarrinho(@PathVariable Long carrinhoId,
			@PathVariable Long produtoId) {
		try {
			Produto produtoDeletado = carrinhoService.removerProdutoDoCarrinho(carrinhoId, produtoId);
			return ResponseEntity.ok(produtoDeletado);
		} catch (CarrinhoNotFoundException e) {
			return ResponseEntity.notFound().build();
		} catch (ProdutoNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping("/{carrinhoId}/finalizarCompra")
	public ResponseEntity<Double> finalizarCompra(@PathVariable Long carrinhoId) {
		try {
			double precoTotal = carrinhoService.finalizarCompra(carrinhoId);
			return ResponseEntity.ok(precoTotal);
		} catch (CarrinhoNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}

}
