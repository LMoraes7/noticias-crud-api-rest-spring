package br.com.projeto.crud.noticia.data.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.projeto.crud.noticia.modelo.Entidade;

public interface DataService {

	Page<? extends Entidade> consultarTodos(Pageable pageable);
	
	Optional<? extends Entidade> consultarPorId(Long id);
	
	void deletarPorId(Long id);
}