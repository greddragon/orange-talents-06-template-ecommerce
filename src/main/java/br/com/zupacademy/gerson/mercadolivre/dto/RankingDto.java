package br.com.zupacademy.gerson.mercadolivre.dto;

import javax.validation.constraints.NotNull;

public class RankingDto {

	@NotNull
	private Long id_compra;
	@NotNull
	private Long id_vendedor;

	public RankingDto(@NotNull Long id_compra, @NotNull Long id_vendedor) {
		super();
		this.id_compra = id_compra;
		this.id_vendedor = id_vendedor;
	}

	public Long getId_compra() {
		return id_compra;
	}

	public Long getId_vendedor() {
		return id_vendedor;
	}

	@Override
	public String toString() {
		return "RankingDto [id_compra=" + id_compra + ", id_vendedor=" + id_vendedor + "]";
	}

}
