package br.com.zupacademy.gerson.mercadolivre.dto;

public class TokenDto {

	private String tipo;
	private String token;

	public TokenDto(String tipo, String token) {
		this.tipo = tipo;
		this.token = token;
	}

	public String getTipo() {
		return tipo;
	}

	public String getToken() {
		return token;
	}
	
	

}
