package br.com.zupacademy.gerson.mercadolivre.dto;

import java.math.BigDecimal;

import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import br.com.zupacademy.gerson.mercadolivre.enumerated.Gateaway;
import br.com.zupacademy.gerson.mercadolivre.modelo.Compra;
import br.com.zupacademy.gerson.mercadolivre.modelo.Produto;
import br.com.zupacademy.gerson.mercadolivre.modelo.Usuario;

public class compraDto {

	@NotNull
	@Positive
	private int quantidade;
	@NotNull
	private BigDecimal precoMomento;
	@NotNull
	private Long idProduto;
	@Enumerated
	@NotNull
	private Gateaway gateaway;

	public compraDto(@NotNull @Positive int quantidade, @NotNull BigDecimal precoMomento, @NotNull Long idProduto,
			@NotNull Gateaway gateaway) {
		super();
		this.quantidade = quantidade;
		this.precoMomento = precoMomento;
		this.idProduto = idProduto;
		this.gateaway = gateaway;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public BigDecimal getPrecoMomento() {
		return precoMomento;
	}

	public Long getIdProduto() {
		return idProduto;
	}

	public Gateaway getGateaway() {
		return gateaway;
	}

	public Compra toCompra(Produto produto, Usuario user) {

		return new Compra(quantidade, precoMomento, gateaway, produto, user);
	}

}
