package br.com.projeto.crud.noticia.dto;

public class TokenDto {
	
	private String token;
	private String tipo;

	public TokenDto(String token, String tipo) {
		this.token = token;
		this.tipo = tipo;
	}
	
	public String getTipo() {
		return tipo;
	}
	
	public String getToken() {
		return token;
	}
}
