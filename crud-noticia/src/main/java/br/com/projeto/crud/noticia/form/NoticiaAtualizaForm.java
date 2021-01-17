package br.com.projeto.crud.noticia.form;

import javax.validation.constraints.NotBlank;

import br.com.projeto.crud.noticia.modelo.Noticia;

public class NoticiaAtualizaForm {

	@NotBlank(message = "O campo manchete não pode ser vazio")
	private String manchete;
	@NotBlank(message = "O campo descrição não pode ser vazio")
	private String descricao;

	public String getManchete() {return manchete;}

	public void setManchete(String manchete) {this.manchete = manchete;}

	public String getDescricao() {return descricao;}

	public void setDescricao(String descricao) {this.descricao = descricao;}

	public Noticia atualizar(Noticia noticia) {
		noticia.setManchete(this.manchete);
		noticia.setDescricao(this.descricao);
		return noticia;
	}
}