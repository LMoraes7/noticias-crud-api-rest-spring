package br.com.projeto.crud.noticia.seguranca.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.projeto.crud.noticia.data.service.UsuarioDataService;
import br.com.projeto.crud.noticia.modelo.Usuario;

@Service
public class AutenticacaoService implements UserDetailsService{

	@Autowired
	private UsuarioDataService usuarioService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Usuario> usuarioOptional = this.usuarioService.consultarPorUsername(username);
		if(usuarioOptional.isPresent())
			return usuarioOptional.get();
		throw new UsernameNotFoundException("Dados Inválidos!");
	}

}
