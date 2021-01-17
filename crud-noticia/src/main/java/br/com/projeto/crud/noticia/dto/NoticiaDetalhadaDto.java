package br.com.projeto.crud.noticia.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import br.com.projeto.crud.noticia.modelo.Noticia;

public class NoticiaDetalhadaDto {

	private Long id;
	private String manchete;
	private String descricao;
	private UsuarioDto autor;
	private LocalDate data;
	private CategoriaDto categoria;
	private List<ComentarioNoticiaDto> comentarios = new ArrayList<>();

	
	public NoticiaDetalhadaDto(Noticia noticia) {
		this.id = noticia.getId(); this.manchete = noticia.getManchete();
		this.descricao = noticia.getDescricao(); this.autor = new UsuarioDto(noticia.getAutor());
		this.data = noticia.getData(); this.categoria = new CategoriaDto(noticia.getCategoria());
		this.comentarios = noticia.getComentarios().stream().map(ComentarioNoticiaDto::new).collect(Collectors.toList());
	}

	public Long getId() {return id;}

	public String getManchete() {return manchete;}

	public String getDescricao() {return descricao;}

	public UsuarioDto getAutor() {return autor;}

	public LocalDate getData() {return data;}

	public CategoriaDto getCategoria() {return categoria;}

	public List<ComentarioNoticiaDto> getComentarios() {return Collections.unmodifiableList(comentarios);}
}