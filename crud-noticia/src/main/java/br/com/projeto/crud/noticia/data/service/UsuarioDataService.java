package br.com.projeto.crud.noticia.data.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.projeto.crud.noticia.modelo.Usuario;
import br.com.projeto.crud.noticia.repository.UsuarioRepository;

@Service
public class UsuarioDataService implements DataService{

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public Optional<Usuario> consultarPorUsername(String username) {
		return this.usuarioRepository.findUsername(username);
	}

	@Override
	public Optional<Usuario> consultarPorId(Long id) {
		return this.usuarioRepository.findById(id);
	}

	@Override
	public Page<Usuario> consultarTodos(Pageable pageable) {
		return this.usuarioRepository.findAll(pageable);
	}

	@Override
	public void deletarPorId(Long id) {
		this.usuarioRepository.deleteById(id);
	}
}