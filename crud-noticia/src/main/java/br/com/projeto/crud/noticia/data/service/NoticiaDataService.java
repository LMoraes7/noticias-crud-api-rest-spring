package br.com.projeto.crud.noticia.data.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.projeto.crud.noticia.modelo.Noticia;
import br.com.projeto.crud.noticia.repository.NoticiaRepository;
import br.com.projeto.crud.noticia.specification.NoticiaSpecification;

@Service
public class NoticiaDataService implements DataService{

	@Autowired
	private NoticiaRepository noticiaRepository;
	
	@Override
	public Page<Noticia> consultarTodos(Pageable pageable) {
		return this.noticiaRepository.findAll(pageable);
	}

	@Override
	public Optional<Noticia> consultarPorId(Long id) {
		return this.noticiaRepository.findId(id);
	}

	@Override
	public void deletarPorId(Long id) {
		this.noticiaRepository.deleteById(id);
	}
	
	public Page<Noticia> consultarPorFiltros(String query, Pageable pageable) {
		return this.noticiaRepository.findAll(NoticiaSpecification.manchete(query)
											.or(NoticiaSpecification.descricao(query)), pageable);
	}

	public void salvar(Noticia noticia) {
		this.noticiaRepository.save(noticia);
	}
}