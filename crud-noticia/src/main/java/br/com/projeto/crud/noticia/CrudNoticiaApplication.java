package br.com.projeto.crud.noticia;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

import br.com.projeto.crud.noticia.data.service.CategoriaDataService;
import br.com.projeto.crud.noticia.data.service.ComentarioDataService;
import br.com.projeto.crud.noticia.data.service.NoticiaDataService;
import br.com.projeto.crud.noticia.data.service.UsuarioDataService;
import br.com.projeto.crud.noticia.modelo.Categoria;
import br.com.projeto.crud.noticia.modelo.Comentario;
import br.com.projeto.crud.noticia.modelo.Noticia;
import br.com.projeto.crud.noticia.modelo.Usuario;

@SpringBootApplication
@EnableSpringDataWebSupport
public class CrudNoticiaApplication implements CommandLineRunner{
	
	@Autowired
	private NoticiaDataService noticiaDataService;
	
	@Autowired
	private CategoriaDataService categoriaDataService;
	
	@Autowired
	private UsuarioDataService usuarioDataService;
	
	@Autowired
	private ComentarioDataService comentarioDataService;

	public static void main(String[] args) {
		SpringApplication.run(CrudNoticiaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria categoriaEsporte = this.categoriaDataService.consultarPorId(1L).get();
		Categoria categoriaPolitica = this.categoriaDataService.consultarPorId(3L).get();
		
		Usuario diego = this.usuarioDataService.consultarPorId(1L).get();
		Usuario carlos = this.usuarioDataService.consultarPorId(2L).get();

		Noticia noticia1 = new Noticia("Brasil campeão do mundo!", "O Brasil venceu por 3x0 a França na final do mundial.", categoriaEsporte, diego);
		Noticia noticia2 = new Noticia("Cristiano Ronaldo se aposentou!", "CR7 resolveu se aposentar aos 37 anos.", categoriaEsporte, carlos);
		Noticia noticia3 = new Noticia("China no topo!", "China supera PIB dos EUA e se torna a maior economia mundia.", categoriaPolitica, diego);
		
		List<Noticia> noticias = Arrays.asList(noticia1, noticia2, noticia3);
		
		noticias.stream().forEach(noticia -> {
			this.noticiaDataService.salvar(noticia);
		});
		
		Comentario comentario = new Comentario("Que ótimo, somos Hexacampeõs!", noticia1, carlos);
		this.comentarioDataService.salvar(comentario);
		noticia1.addComentario(comentario);
	}
}