package br.com.projeto.crud.noticia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.projeto.crud.noticia.modelo.Comentario;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Long>{

}
