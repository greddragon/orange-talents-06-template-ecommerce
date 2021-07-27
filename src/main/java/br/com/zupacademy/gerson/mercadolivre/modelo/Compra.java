package br.com.zupacademy.gerson.mercadolivre.modelo;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import br.com.zupacademy.gerson.mercadolivre.enumerated.Gateaway;

@Entity
public class Compra {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	@Positive
	private int quantidade;
	@NotNull
	private BigDecimal precoMomento;
	
	private Gateaway gateaway;
	
	@ManyToOne
	@Valid
	private Produto produto;
	@ManyToOne
	private Usuario usuario;
	public Compra(@NotNull @Positive int quantidade, @NotNull BigDecimal precoMomento, @NotBlank Gateaway gateaway,
			@Valid Produto produto, Usuario usuario) {
		super();
		this.quantidade = quantidade;
		this.precoMomento = precoMomento;
		this.gateaway = gateaway;
		this.produto = produto;
		this.usuario = usuario;
	}
	public Long getId() {
		return id;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public BigDecimal getPrecoMomento() {
		return precoMomento;
	}
	public Gateaway getGateaway() {
		return gateaway;
	}
	public Produto getProduto() {
		return produto;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	
	
}
