package br.com.projeto.crud.noticia.modelo;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Comentario implements Entidade{

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String comentario;
	private LocalDate data = LocalDate.now();
	
	@ManyToOne
	private Usuario autor;
	
	@ManyToOne
	private Noticia noticia;
	
	public Comentario() {}
	
	public Comentario(String comentario, Noticia noticia, Usuario autor) {
		this.comentario = comentario; this.noticia = noticia;
		this.autor = autor;
	}

	public Long getId() {return id;}

	public String getComentario() {return comentario;}

	public void setComentario(String comentario) {this.comentario = comentario;}

	public LocalDate getData() {return data;}
	
	public void setData(LocalDate data) {this.data = data;}

	public Usuario getAutor() {return autor;}

	public Noticia getNoticia() {return noticia;}

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
		Comentario other = (Comentario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}