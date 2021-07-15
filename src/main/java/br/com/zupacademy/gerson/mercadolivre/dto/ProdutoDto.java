package br.com.zupacademy.gerson.mercadolivre.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

import br.com.zupacademy.gerson.mercadolivre.modelo.Categoria;
import br.com.zupacademy.gerson.mercadolivre.modelo.Produto;
import br.com.zupacademy.gerson.mercadolivre.modelo.Usuario;
import br.com.zupacademy.gerson.mercadolivre.service.TokenService;
import br.com.zupacademy.gerson.mercadolivre.validacao.ExisteId;

public class ProdutoDto {

	@NotBlank
	private String nome;
	@NotNull
	@Positive
	private BigDecimal valor;
	@NotNull
	@Positive
	private int quantidade;
	@NotBlank
	@Length(max = 1000)
	private String descricao;
	@NotNull
	@ExisteId(classe = Categoria.class, value = "id")
	private Long idCategoria;
	@Size(min = 3)
	private List<ProdutoCaracteristicasDto> caracteristicas = new ArrayList<>();

	public ProdutoDto(@NotBlank String nome, @NotNull @Positive BigDecimal valor, @NotNull @Positive int quantidade,
			@NotBlank @Length(max = 1000) String descricao, @NotNull Long idCategoria,
			@Size(min = 3) List<ProdutoCaracteristicasDto> caracteristicas) {
		super();
		this.nome = nome;
		this.valor = valor;
		this.quantidade = quantidade;
		this.descricao = descricao;
		this.idCategoria = idCategoria;
		this.caracteristicas.addAll(caracteristicas);
	}

	public String getNome() {
		return nome;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public String getDescricao() {
		return descricao;
	}

	public Long getIdCategoria() {
		return idCategoria;
	}

	public List<ProdutoCaracteristicasDto> getCaracteristicas() {
		return caracteristicas;
	}

	public Produto getProduto(HttpServletRequest request, TokenService tokenService, EntityManager em) {

		String token = request.getHeader("Authorization");
		Long id_usuario = tokenService.getIdToken(token.substring(7, token.length()));

		Usuario usuario = em.find(Usuario.class, id_usuario);
		Categoria categoria = em.find(Categoria.class, idCategoria);

		return new Produto(nome, valor, quantidade, descricao, categoria, usuario, caracteristicas);
	}

}
