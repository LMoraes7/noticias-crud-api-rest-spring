package br.com.projeto.crud.noticia.data.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projeto.crud.noticia.modelo.Categoria;
import br.com.projeto.crud.noticia.repository.CategoriaRepository;

@Service
public class CategoriaDataService {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public Optional<Categoria> consultarPorId(Long id) {
		return this.categoriaRepository.findById(id);
	}
}
