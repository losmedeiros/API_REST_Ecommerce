package br.armando.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.armando.ecommerce.entity.Carrinho;
import br.armando.ecommerce.entity.Produto;
import br.armando.ecommerce.excecoes.CarrinhoNotFoundException;
import br.armando.ecommerce.excecoes.ProdutoNotFoundException;
import br.armando.ecommerce.repository.CarrinhoRepository;
import br.armando.ecommerce.repository.ProdutoRepository;

@Service
public class CarrinhoService {
	@Autowired
	private final CarrinhoRepository carrinhoRepository;
	private final ProdutoRepository produtoRepository;

	public CarrinhoService(CarrinhoRepository carrinhoRepository, ProdutoRepository produtoRepository) {
		this.carrinhoRepository = carrinhoRepository;
		this.produtoRepository = produtoRepository;
	}

	public void adicionarProdutoAoCarrinho(Carrinho carrinho, Produto produto) {
		carrinho.getProdutos().add(produto); // Adiciona o produto ao carrinho
		carrinhoRepository.save(carrinho); // Salva as alterações no banco de dados
	}

	public Produto removerProdutoDoCarrinho(Long carrinhoId, Long produtoId) {
		// Busca o carrinho pelo ID
		Optional<Carrinho> carrinhoOptional = carrinhoRepository.findById(carrinhoId);
		Optional<Produto> produtoOptional = produtoRepository.findById(produtoId);

		if (carrinhoOptional.isPresent() && produtoOptional.isPresent()) {
			Carrinho carrinho = carrinhoOptional.get();
			Produto produto = produtoOptional.get();

			// Verifica se o produto está presente no carrinho
			if (carrinho.getProdutos().contains(produto)) {
				// Remove o produto do carrinho
				carrinho.getProdutos().remove(produto);
				carrinhoRepository.save(carrinho); // Salva as alterações no banco de dados
				// Retorna o produto que foi removido do carrinho
				return produto;
			} else {
				throw new ProdutoNotFoundException("Produto não encontrado no carrinho");
			}
		} else {
			throw new CarrinhoNotFoundException("Carrinho ou Produto não encontrado");
		}
	}

	public List<Produto> listarProdutosDoCarrinho(Long carrinhoId) {
		// Busca o carrinho pelo ID
		Optional<Carrinho> carrinhoOptional = carrinhoRepository.findById(carrinhoId);

		if (carrinhoOptional.isPresent()) {
			Carrinho carrinho = carrinhoOptional.get();
			return carrinho.getProdutos(); // Retorna a lista de produtos do carrinho
		} else {
			throw new CarrinhoNotFoundException("Carrinho não encontrado");
		}
	}

	public double finalizarCompra(Long carrinhoId) {
		// Busca o carrinho pelo ID
		Optional<Carrinho> carrinhoOptional = carrinhoRepository.findById(carrinhoId);

		if (carrinhoOptional.isPresent()) {
			Carrinho carrinho = carrinhoOptional.get();
			List<Produto> produtos = carrinho.getProdutos();

			// Calcula o preço total dos produtos no carrinho
			double precoTotal = produtos.stream().mapToDouble(Produto::getPreco).sum();

			// Limpa os produtos do carrinho
			carrinho.getProdutos().clear();
			carrinhoRepository.save(carrinho); // Salva as alterações no banco de dados

			return precoTotal;
		} else {
			throw new CarrinhoNotFoundException("Carrinho não encontrado");
		}
	}

}
