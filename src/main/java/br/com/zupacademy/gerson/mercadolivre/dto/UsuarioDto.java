package br.com.zupacademy.gerson.mercadolivre.dto;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import br.com.zupacademy.gerson.mercadolivre.modelo.Usuario;
import br.com.zupacademy.gerson.mercadolivre.service.TokenService;
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

	public static Usuario getUsuarioLogado(HttpServletRequest request, TokenService tokenService, EntityManager em) {

		String token = request.getHeader("Authorization");
		Long id_usuario = tokenService.getIdToken(token.substring(7, token.length()));
		
		Usuario usuarioLogado = em.find(Usuario.class, id_usuario);
		
		return usuarioLogado;
	}

}
