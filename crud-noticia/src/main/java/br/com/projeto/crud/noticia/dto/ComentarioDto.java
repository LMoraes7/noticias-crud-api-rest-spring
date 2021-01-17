package br.com.projeto.crud.noticia.dto;

import java.time.LocalDate;

import br.com.projeto.crud.noticia.modelo.Comentario;

public class ComentarioDto {

	private Long id;
	private String comentario;
	private LocalDate data;
	private UsuarioDto autor;
	private NoticiaComentarioDto noticia;
	
	public ComentarioDto(Comentario comentario) {
		this.id = comentario.getId(); this.comentario = comentario.getComentario();
		this.data = comentario.getData(); this.autor = new UsuarioDto(comentario.getAutor());
		this.noticia = new NoticiaComentarioDto(comentario.getNoticia());
	}

	public Long getId() {return id;}

	public String getComentario() {return comentario;}

	public LocalDate getData() {return data;}

	public UsuarioDto getAutor() {return autor;}

	public NoticiaComentarioDto getNoticia() {return noticia;}
}