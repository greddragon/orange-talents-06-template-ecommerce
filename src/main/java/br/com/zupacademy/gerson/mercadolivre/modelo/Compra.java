package br.com.zupacademy.gerson.mercadolivre.modelo;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.springframework.util.Assert;

import br.com.zupacademy.gerson.mercadolivre.dto.RetornoPagamento;
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

	@OneToMany(mappedBy = "compra", cascade = CascadeType.MERGE)
	private Set<Transacao> transacoes = new HashSet<>();

	@Deprecated
	public Compra() {

	}

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
	
	
	
	public Set<Transacao> getTransacoes() {
		return transacoes;
	}

	public void realizarPagamento(RetornoPagamento pag) {
		Transacao novatransacao = pag.toTrasacao(this);
		
		this.transacoes.forEach(transacao -> {
			if(transacao.getIdTransacaoGateway().equals(novatransacao.getIdTransacaoGateway())) {
				System.out.println(transacao);
				Assert.state(false,"a transação só pode ser processado uma vez " + novatransacao);
			}
			
		});

		
		
		Assert.state(transacoesProcessadasComSucesso().isEmpty(),"Compra já processada com sucesso");

		this.transacoes.add(novatransacao);

	}

	private Set<Transacao> transacoesProcessadasComSucesso() {
		Set<Transacao> processadasComSucesso = this.transacoes.stream()
				.filter(Transacao::processadoComSucesso).collect(Collectors.toSet());
		
		return processadasComSucesso;
	}
	
	public boolean processadasComSucesso() {
		return !transacoesProcessadasComSucesso().isEmpty();
	}


}
