package br.com.projeto.crud.noticia.dto;

import java.time.LocalDate;

import br.com.projeto.crud.noticia.modelo.Noticia;

public class NoticiaComentarioDto {

	private Long id;
	private String manchete;
	private LocalDate data;

	public NoticiaComentarioDto(Noticia noticia) {
		this.id = noticia.getId(); this.manchete = noticia.getManchete();
		this.data = noticia.getData();
	}

	public Long getId() {return id;}

	public void setId(Long id) {this.id = id;}

	public String getManchete() {return manchete;}

	public void setManchete(String manchete) {this.manchete = manchete;}

	public LocalDate getData() {return data;}

	public void setData(LocalDate data) {this.data = data;}
}