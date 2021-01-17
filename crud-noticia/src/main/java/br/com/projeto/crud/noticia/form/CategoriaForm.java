package br.com.projeto.crud.noticia.form;

import javax.validation.constraints.NotBlank;

public class CategoriaForm {

	@NotBlank(message = "O campo categoria não pode ser vazio")
	private Long id;

	public Long getId() {return id;}

	public void setId(Long id) {this.id = id;}
}