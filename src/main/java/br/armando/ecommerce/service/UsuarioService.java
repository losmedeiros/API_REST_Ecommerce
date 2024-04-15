package br.armando.ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.armando.ecommerce.entity.Carrinho;
import br.armando.ecommerce.entity.Usuario;
import br.armando.ecommerce.excecoes.UsuarioExistenteException;
import br.armando.ecommerce.repository.CarrinhoRepository;
import br.armando.ecommerce.repository.UsuarioRepository;

@Service
public class UsuarioService {
	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private CarrinhoRepository carrinhoRepository;

//	public UsuarioService(UsuarioRepository usuarioRepository,CarrinhoRepository carrinhoRepository) {
//		this.usuarioRepository = usuarioRepository;
//		this.carrinhoRepository = carrinhoRepository;

	@Transactional
	public Usuario cadastrarUsuario(Usuario usuario) {
		// Verifica se já existe um usuário com o mesmo nome
		if (usuarioRepository.findByNome(usuario.getNome()) != null) {
			throw new UsuarioExistenteException("Já existe um usuário com este nome");
		}
		// Cria um carrinho
        Carrinho carrinho = new Carrinho();

        // Associa o usuário ao carrinho e o carrinho ao usuário
        usuario.setCarrinho(carrinho);
        carrinho.setUsuario(usuario);

        // Salva o carrinho no banco de dados
        carrinhoRepository.save(carrinho);

        // Salva o usuário no banco de dados (a cascata cuidará de salvar o carrinho também)
        return usuarioRepository.save(usuario);
	}

	public Usuario fazerLogin(String nome, String senha) {

		// Busca o usuário pelo nome de usuário
		Usuario usuario = usuarioRepository.findByNome(nome);

		// Verifica se o usuário existe e se a senha está correta
		if (usuario != null && usuario.getSenha().equals(senha)) {
			return usuario; // Retorna o usuário se o login for bem-sucedido
		} else {
			return null; // Retorna null se o login falhar
		}
	}
}

