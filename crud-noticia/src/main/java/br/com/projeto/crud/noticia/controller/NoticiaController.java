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

import br.com.projeto.crud.noticia.data.service.CategoriaDataService;
import br.com.projeto.crud.noticia.data.service.NoticiaDataService;
import br.com.projeto.crud.noticia.dto.NoticiaDetalhadaDto;
import br.com.projeto.crud.noticia.dto.NoticiaDto;
import br.com.projeto.crud.noticia.form.NoticiaAtualizaForm;
import br.com.projeto.crud.noticia.form.NoticiaForm;
import br.com.projeto.crud.noticia.modelo.Noticia;
import br.com.projeto.crud.noticia.modelo.Usuario;

@RestController
@RequestMapping("/noticias")
public class NoticiaController {

	@Autowired
	private NoticiaDataService noticiaService;
	
	@Autowired
	private CategoriaDataService categoriaService;
	
	@GetMapping("/{id}")
	public ResponseEntity<NoticiaDetalhadaDto> listar(@PathVariable("id") Long id) {
		Optional<Noticia> noticiaOptional = this.noticiaService.consultarPorId(id);
		if(noticiaOptional.isPresent())
			return ResponseEntity.ok(new NoticiaDetalhadaDto(noticiaOptional.get()));
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping("/cadastrar")
	@Transactional
	public ResponseEntity<NoticiaDto> cadastrar(@RequestBody @Valid NoticiaForm noticiaForm, UriComponentsBuilder uriBuilder) {
		Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Noticia noticia = noticiaForm.converter(this.categoriaService, usuario);
		this.noticiaService.salvar(noticia);
		URI uri = uriBuilder.path("/noticias/{id}").buildAndExpand(noticia.getId()).toUri();
		return ResponseEntity.created(uri).body(new NoticiaDto(noticia));
	}
	
	@PutMapping("/atualizar/{id}")
	@Transactional
	public ResponseEntity<NoticiaDto> atualizar(@PathVariable("id") Long id, @RequestBody @Valid NoticiaAtualizaForm noticiaForm) {
		Optional<Noticia> noticiaOptional = this.noticiaService.consultarPorId(id);
		if(noticiaOptional.isPresent()) {
			Noticia noticia = noticiaOptional.get();
			noticia = noticiaForm.atualizar(noticia);
			return ResponseEntity.ok(new NoticiaDto(noticia));
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/deletar/{id}")
	@Transactional
	public ResponseEntity<?> deletar(@PathVariable("id") Long id) {
		Optional<Noticia> noticiaOptional = this.noticiaService.consultarPorId(id);
		if(noticiaOptional.isPresent()) {
			this.noticiaService.deletarPorId(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
}