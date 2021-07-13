package br.com.zupacademy.gerson.mercadolivre.dto;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class LoginDto {

	private String email;
	private String senha;

	public LoginDto(String email, String senha) {
		super();
		this.email = email;
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public String getSenha() {
		return senha;
	}

	public UsernamePasswordAuthenticationToken toDadosToken() {
		return new UsernamePasswordAuthenticationToken(email, senha);
	}

}
