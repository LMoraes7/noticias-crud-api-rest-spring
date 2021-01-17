package br.com.projeto.crud.noticia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.projeto.crud.noticia.data.service.NoticiaDataService;
import br.com.projeto.crud.noticia.dto.NoticiaDto;
import br.com.projeto.crud.noticia.modelo.Noticia;

@RestController
@RequestMapping("/home")
public class HomeController {

	@Autowired
	private NoticiaDataService noticiaservice;
	
	@GetMapping
	public ResponseEntity<Page<NoticiaDto>> lista(
			@PageableDefault(page = 0, size = 5, direction = Direction.DESC, sort = "data") Pageable pageable) {
		Page<Noticia> noticias = this.noticiaservice.consultarTodos(pageable);
		return ResponseEntity.ok(NoticiaDto.converter(noticias));
	}
	
	@GetMapping("/{page}")
	public ResponseEntity<Page<NoticiaDto>> listarPage(@PathVariable("page") Integer page) {
		Pageable pageable = PageRequest.of(page, 5, Direction.DESC, "data");
		return ResponseEntity.ok(NoticiaDto.converter(this.noticiaservice.consultarTodos(pageable)));
	}
	
	@GetMapping("/busca")
	public ResponseEntity<Page<NoticiaDto>> listarBusca(@RequestParam("query") String query,
			@PageableDefault(page = 0, size = 5, direction = Direction.DESC, sort = "data") Pageable pageable) {
		Page<Noticia> noticias = this.noticiaservice.consultarPorFiltros(query, pageable);
		return ResponseEntity.ok(NoticiaDto.converter(noticias));
	}
	
	@GetMapping("/busca/{page}")
	public ResponseEntity<Page<NoticiaDto>> listarBuscaPage(@PathVariable("page") Integer page,
			@RequestParam("query") String query) {
		Pageable pageable = PageRequest.of(page, 5, Direction.DESC, "data");
		Page<Noticia> noticias = this.noticiaservice.consultarPorFiltros(query, pageable);
		return ResponseEntity.ok(NoticiaDto.converter(noticias));
	}
}