package br.com.zupacademy.gerson.mercadolivre.dto;

import javax.validation.constraints.NotBlank;

import br.com.zupacademy.gerson.mercadolivre.modelo.Produto;
import br.com.zupacademy.gerson.mercadolivre.modelo.ProdutoCaracteristica;

public class ProdutoCaracteristicasDto {

	@NotBlank
	private String nome;
	@NotBlank
	private String descricao;

	public ProdutoCaracteristicasDto(@NotBlank String nome, @NotBlank String descricao) {
		super();
		this.nome = nome;
		this.descricao = descricao;
	}

	public String getNome() {
		return nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public ProdutoCaracteristica toCaracteristica(Produto produto) {

		return new ProdutoCaracteristica(nome, descricao, produto);
	}

}
