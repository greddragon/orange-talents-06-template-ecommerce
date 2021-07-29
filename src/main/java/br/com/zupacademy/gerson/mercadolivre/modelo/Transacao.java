package br.com.zupacademy.gerson.mercadolivre.modelo;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.zupacademy.gerson.mercadolivre.enumerated.StatusRetorno;

@Entity
public class Transacao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	private String idTransacaoGateway;
	@NotNull
	@Enumerated(EnumType.STRING)
	private StatusRetorno status;
	
	
	@NotNull
	@ManyToOne
	private Compra compra;
	
	private LocalDateTime instante;
	
	@Deprecated
	public Transacao() {
		
	}
	
	public Transacao(@NotBlank String idTransacaoGateway, @NotNull StatusRetorno status, @NotNull Compra compra) {
		super();
		this.idTransacaoGateway = idTransacaoGateway;
		this.status = status;
		this.compra = compra;
		this.instante = LocalDateTime.now();
	}
	
	public boolean processadoComSucesso() {
		return this.status.equals(StatusRetorno.SUCESSO);
	}



	public String getIdTransacaoGateway() {
		return idTransacaoGateway;
	}



	@Override
	public String toString() {
		return "Transacao [idTransacaoGateway=" + idTransacaoGateway + ", status=" + status + ", instante=" + instante
				+ "]";
	}

	
	

}
