package br.com.projeto.crud.noticia.specification;

import org.springframework.data.jpa.domain.Specification;

import br.com.projeto.crud.noticia.modelo.Noticia;

public class NoticiaSpecification {

	public static Specification<Noticia> manchete(String manchete) {
		return (root, criteriaQuery, criteriaBuilder) -> 
			criteriaBuilder.like(root.get("manchete"), "%"+manchete+"%");
	}
	
	public static Specification<Noticia> descricao(String descricao) {
		return (root, criteriaQuery, criteriaBuilder) ->
			criteriaBuilder.like(root.get("descricao"), "%"+descricao+"%");
	}
}
