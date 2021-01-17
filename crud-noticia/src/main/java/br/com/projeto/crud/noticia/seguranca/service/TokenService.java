package br.com.projeto.crud.noticia.seguranca.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.com.projeto.crud.noticia.modelo.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {
	
	@Value("${crud.noticia.jwt.expiration}")
	private String expiracao;
	
	@Value("${crud.noticia.jwt.secret}")
	private String secret;

	public String gerar(Authentication authentication) {
		Usuario usuario = (Usuario) authentication.getPrincipal();
		Date agora = new Date();
		Date expiracao = new Date(agora.getTime() + Long.parseLong(this.expiracao));
		return Jwts.builder()
				.setIssuer("API de Notícias Portifólio")
				.setSubject(usuario.getId().toString())
				.setIssuedAt(agora)
				.setExpiration(expiracao)
				.signWith(SignatureAlgorithm.HS256, this.secret)
				.compact();
	}

	public boolean validarToken(String token) {
		try {
			Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public Long recuperarIdUsuario(String token) {
		Jws<Claims> claimsJws = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
		String idUsuario = claimsJws.getBody().getSubject();
		return Long.parseLong(idUsuario);
	}
}
