package br.com.projeto.crud.noticia.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.projeto.crud.noticia.modelo.Noticia;

@Repository
public interface NoticiaRepository extends JpaRepository<Noticia, Long>, JpaSpecificationExecutor<Noticia>{

	@Query("SELECT n FROM Noticia n LEFT JOIN FETCH n.comentarios WHERE n.id = :id")
	Optional<Noticia> findId(@Param("id") Long id);
}
