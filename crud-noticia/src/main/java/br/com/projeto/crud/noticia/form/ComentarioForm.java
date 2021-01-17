package br.com.projeto.crud.noticia.form;

import java.time.LocalDate;

import br.com.projeto.crud.noticia.modelo.Comentario;
import br.com.projeto.crud.noticia.modelo.Noticia;
import br.com.projeto.crud.noticia.modelo.Usuario;

public class ComentarioForm {

	private String comentario;

	public String getComentario() {return comentario;}

	public void setComentario(String comentario) {this.comentario = comentario;}

	public Comentario converter(Noticia noticia, Usuario usuario) {
		return new Comentario(this.comentario, noticia, usuario);
	}

	public Comentario atualizar(Comentario comentario) {
		comentario.setComentario(this.comentario);
		comentario.setData(LocalDate.now());
		return comentario;
	}
}
