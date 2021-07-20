package br.com.zupacademy.gerson.mercadolivre.dto;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;
import org.springframework.util.Assert;

import br.com.zupacademy.gerson.mercadolivre.modelo.Opiniao;
import br.com.zupacademy.gerson.mercadolivre.modelo.Produto;
import br.com.zupacademy.gerson.mercadolivre.modelo.Usuario;
import br.com.zupacademy.gerson.mercadolivre.service.TokenService;

public class opiniaoDto {
	
	@NotBlank
	private String titulo;
	
	@Min(1)
	@Max(5)
	private int nota;
	
	@Length(max = 500)
	private String descricao;

	public opiniaoDto(@NotBlank String titulo, @Min(1) @Max(5) int nota, @Length(max = 500) String descricao) {
		super();
		this.titulo = titulo;
		this.nota = nota;
		this.descricao = descricao;
	}

	public String getTitulo() {
		return titulo;
	}

	public int getNota() {
		return nota;
	}

	public String getDescricao() {
		return descricao;
	}

	public Opiniao getOpiniao(Long id, HttpServletRequest request, EntityManager em, TokenService tokenService) {
		
		Produto produto = em.find(Produto.class, id);
		
		String token = request.getHeader("Authorization");
		Long id_usuario = tokenService.getIdToken(token.substring(7, token.length()));
		Usuario usuario = em.find(Usuario.class, id_usuario);
		
		Assert.state(produto != null, "Produto não existe");
		
		
		return new Opiniao(titulo, nota, descricao, produto, usuario);
	}
	
	
	

}
