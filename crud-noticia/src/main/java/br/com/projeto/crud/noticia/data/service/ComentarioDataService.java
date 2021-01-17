package br.com.projeto.crud.noticia.data.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.projeto.crud.noticia.modelo.Comentario;
import br.com.projeto.crud.noticia.repository.ComentarioRepository;

@Service
public class ComentarioDataService implements DataService{

	@Autowired
	private ComentarioRepository comentarioRepository;
	
	@Override
	public Page<Comentario> consultarTodos(Pageable pageable) {
		return this.comentarioRepository.findAll(pageable);
	}

	@Override
	public Optional<Comentario> consultarPorId(Long id) {
		return this.comentarioRepository.findById(id);
	}

	@Override
	public void deletarPorId(Long id) {
		this.comentarioRepository.deleteById(id);
	}

	public void salvar(Comentario comentario) {
		this.comentarioRepository.save(comentario);
	}
}