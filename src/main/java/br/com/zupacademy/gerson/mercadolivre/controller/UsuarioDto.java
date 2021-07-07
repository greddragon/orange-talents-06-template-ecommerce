package br.com.zupacademy.gerson.mercadolivre.controller;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import br.com.zupacademy.gerson.mercadolivre.validacao.ValorUnico;

public class UsuarioDto {

	@Email
	@NotBlank
	@ValorUnico(classe = Usuario.class, value = "email")
	private String email;
	@NotBlank
	@Length(min = 6)
	private String senha;

	public UsuarioDto(@Email @NotBlank String email, @NotBlank @Length(min = 6) String senha) {
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

	public Usuario toUsuario() {
		
		return new Usuario(email, senha);
	}

}
