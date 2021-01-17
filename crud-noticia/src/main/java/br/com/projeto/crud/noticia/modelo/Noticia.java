package br.com.projeto.crud.noticia.modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Noticia implements Entidade{

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String manchete;
	private String descricao;
	@ManyToOne
	private Usuario autor;
	private LocalDate data = LocalDate.now();
	
	@ManyToOne
	private Categoria categoria;
	
	@OneToMany(mappedBy = "noticia", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Comentario> comentarios = new ArrayList<>();
	
	public Noticia() {}
	
	public Noticia(String manchete, String descricao, Categoria categoria, Usuario autor) {
		this.manchete = manchete; this.descricao = descricao;
		this.categoria = categoria; this.autor = autor;
	}

	public Long getId() {return id;}

	public String getManchete() {return manchete;}

	public void setManchete(String manchete) {this.manchete = manchete;}

	public String getDescricao() {return descricao;}

	public void setDescricao(String descricao) {this.descricao = descricao;}

	public Usuario getAutor() {return autor;}
	
	public LocalDate getData() {return data;}

	public Categoria getCategoria() {return categoria;}

	public List<Comentario> getComentarios() {return Collections.unmodifiableList(comentarios);}
	
	public void addComentario(Comentario comentario) {this.comentarios.add(comentario);}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Noticia other = (Noticia) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}