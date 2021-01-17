package br.com.projeto.crud.noticia.form;

import javax.validation.constraints.NotBlank;

import br.com.projeto.crud.noticia.data.service.CategoriaDataService;
import br.com.projeto.crud.noticia.modelo.Categoria;
import br.com.projeto.crud.noticia.modelo.Noticia;
import br.com.projeto.crud.noticia.modelo.Usuario;

public class NoticiaForm {

	@NotBlank(message = "O campo manchete não pode ser vazio")
	private String manchete;
	@NotBlank(message = "O campo descrição não pode ser vazio")
	private String descricao;
	private CategoriaForm categoria;

	public String getManchete() {return manchete;}

	public void setManchete(String manchete) {this.manchete = manchete;}

	public String getDescricao() {return descricao;}

	public void setDescricao(String descricao) {this.descricao = descricao;}

	public CategoriaForm getCategoria() {return categoria;}

	public void setCategoria(CategoriaForm categoria) {this.categoria = categoria;}

	public Noticia converter(CategoriaDataService categoriaService, Usuario usuario) {
		Categoria categoria = buscarCategoria(categoriaService);
		return new Noticia(this.manchete, this.descricao, categoria, usuario);
	}

	private Categoria buscarCategoria(CategoriaDataService categoriaService) {
		return categoriaService.consultarPorId(this.categoria.getId()).get();
	}
}