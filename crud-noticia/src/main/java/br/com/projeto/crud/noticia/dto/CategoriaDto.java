package br.com.projeto.crud.noticia.dto;

import br.com.projeto.crud.noticia.modelo.Categoria;

public class CategoriaDto {

	private Long id;
	private String categoria;
	
	public CategoriaDto(Categoria categoria) {
		this.id = categoria.getId(); this.categoria = categoria.getCategoria();
	}

	public Long getId() {return id;}

	public String getCategoria() {return categoria;}
}