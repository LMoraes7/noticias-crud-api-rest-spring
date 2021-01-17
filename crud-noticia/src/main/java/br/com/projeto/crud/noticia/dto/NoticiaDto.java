package br.com.projeto.crud.noticia.dto;

import java.time.LocalDate;

import org.springframework.data.domain.Page;

import br.com.projeto.crud.noticia.modelo.Noticia;

public class NoticiaDto {

	public static Page<NoticiaDto> converter(Page<Noticia> noticias) {
		return noticias.map(NoticiaDto::new);
	}
	
	private Long id;
	private String manchete;
	private LocalDate data;
	private CategoriaDto categoria;

	public NoticiaDto(Noticia noticia) {
		this.id = noticia.getId(); this.manchete = noticia.getManchete();
		this.data = noticia.getData(); this.categoria = new CategoriaDto(noticia.getCategoria());
	}

	public Long getId() {return id;}

	public String getManchete() {return manchete;}

	public LocalDate getData() {return data;}

	public CategoriaDto getCategoria() {return categoria;}
}