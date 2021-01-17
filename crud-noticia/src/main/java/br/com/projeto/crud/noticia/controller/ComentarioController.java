package br.com.projeto.crud.noticia.controller;

import java.net.URI;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.projeto.crud.noticia.data.service.ComentarioDataService;
import br.com.projeto.crud.noticia.data.service.NoticiaDataService;
import br.com.projeto.crud.noticia.dto.ComentarioDto;
import br.com.projeto.crud.noticia.form.ComentarioForm;
import br.com.projeto.crud.noticia.modelo.Comentario;
import br.com.projeto.crud.noticia.modelo.Noticia;
import br.com.projeto.crud.noticia.modelo.Usuario;

@RestController
@RequestMapping("/comentarios")
public class ComentarioController {

	@Autowired
	private ComentarioDataService comentarioService;
	
	@Autowired
	private NoticiaDataService noticiaService;
	
	@GetMapping("/{id}")
	public ResponseEntity<ComentarioDto> listarComentario(@PathVariable("id") Long id) {
		Optional<Comentario> comentarioOptional = this.comentarioService.consultarPorId(id);
		if(comentarioOptional.isPresent()) 
			return ResponseEntity.ok(new ComentarioDto(comentarioOptional.get()));
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping("/{idNoticia}/cadastrar")
	@Transactional
	public ResponseEntity<ComentarioDto> cadastrar(@PathVariable("idNoticia") Long id,
			@RequestBody @Valid ComentarioForm comentarioForm, UriComponentsBuilder uriBuilder) {
		Optional<Noticia> noticiaOptional = this.noticiaService.consultarPorId(id);
		if(noticiaOptional.isPresent()) {
			Noticia noticia = noticiaOptional.get();
			Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			Comentario comentario = comentarioForm.converter(noticia, usuario);	
			this.comentarioService.salvar(comentario);
			noticia.addComentario(comentario);
			URI uri = uriBuilder.path("/comentarios/{id}").buildAndExpand(comentario.getId()).toUri();
			return ResponseEntity.created(uri).body(new ComentarioDto(comentario));
		}
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/atualizar/{id}")
	@Transactional
	public ResponseEntity<ComentarioDto> atualizar(@PathVariable("id") Long id, @RequestBody @Valid ComentarioForm comentarioForm) {
		Optional<Comentario> comentarioOptional = this.comentarioService.consultarPorId(id);
		if(comentarioOptional.isPresent()) {
			Comentario comentario = comentarioOptional.get();
			comentario = comentarioForm.atualizar(comentario);
			return ResponseEntity.ok(new ComentarioDto(comentario));
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/deletar/{id}") 
	@Transactional
	public ResponseEntity<?> deletar(@PathVariable("id") Long id) {
		Optional<Comentario> comentarioOptional = this.comentarioService.consultarPorId(id);
		if(comentarioOptional.isPresent()) {
			this.comentarioService.deletarPorId(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
}