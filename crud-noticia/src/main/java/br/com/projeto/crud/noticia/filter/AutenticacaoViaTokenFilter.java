package br.com.projeto.crud.noticia.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.projeto.crud.noticia.data.service.UsuarioDataService;
import br.com.projeto.crud.noticia.modelo.Usuario;
import br.com.projeto.crud.noticia.seguranca.service.TokenService;

public class AutenticacaoViaTokenFilter extends OncePerRequestFilter{
	
	private TokenService tokenService;
	private UsuarioDataService usuarioService;
	
	public AutenticacaoViaTokenFilter(TokenService tokenService, UsuarioDataService usuarioService) {
		this.tokenService = tokenService; this.usuarioService = usuarioService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String token = recuperarToken(request);
		boolean tokenEhValido = tokenService.validarToken(token);
		if(tokenEhValido)
			autenticarCliente(token);
		filterChain.doFilter(request, response);
	}

	private void autenticarCliente(String token) {
		Long id = tokenService.recuperarIdUsuario(token);
		Usuario usuario = this.usuarioService.consultarPorId(id).get();
		UsernamePasswordAuthenticationToken authentication = 
				new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}

	private String recuperarToken(HttpServletRequest request) {
		String cabecalho = request.getHeader("Authorization");
		if(cabecalho == null || cabecalho.isEmpty() || !cabecalho.startsWith("Bearer "))
			return null;
		return cabecalho.substring(7, cabecalho.length());
	}
}