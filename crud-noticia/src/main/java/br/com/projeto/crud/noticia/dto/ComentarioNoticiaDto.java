package br.com.projeto.crud.noticia.dto;

import java.time.LocalDate;

import br.com.projeto.crud.noticia.modelo.Comentario;

public class ComentarioNoticiaDto {

	private Long id;
	private String comentario;
	private LocalDate data;
	private UsuarioDto autor;
	
	public ComentarioNoticiaDto(Comentario comentario) {
		this.id = comentario.getId(); this.comentario = comentario.getComentario();
		this.data = comentario.getData(); this.autor = new UsuarioDto(comentario.getAutor());
	}

	public Long getId() {return id;}

	public String getComentario() {return comentario;}

	public LocalDate getData() {return data;}

	public UsuarioDto getAutor() {return autor;}
}