package br.com.zupacademy.gerson.mercadolivre.dto;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotBlank;

import org.springframework.util.Assert;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.zupacademy.gerson.mercadolivre.modelo.Pergunta;
import br.com.zupacademy.gerson.mercadolivre.modelo.Produto;
import br.com.zupacademy.gerson.mercadolivre.modelo.Usuario;
import br.com.zupacademy.gerson.mercadolivre.service.TokenService;

public class PerguntaDto {
	
	@NotBlank
	private String titulo;

	@JsonCreator
	public PerguntaDto(@JsonProperty("titulo") @NotBlank String titulo) {
		super();
		this.titulo = titulo;
	}

	public String getTitulo() {
		return titulo;
	}

	public Pergunta getPergunta(Long id, HttpServletRequest request, EntityManager em, TokenService tokenService) {
		
		Produto produto = em.find(Produto.class, id);
		
		String token = request.getHeader("Authorization");
		Long id_usuario = tokenService.getIdToken(token.substring(7, token.length()));
		Usuario usuario = em.find(Usuario.class, id_usuario);
		
		Assert.state(produto != null, "Produto não existe");
		
		return new Pergunta(titulo, produto, usuario);
	}
	
	
	
	
}
