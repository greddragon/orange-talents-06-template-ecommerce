package br.com.zupacademy.gerson.mercadolivre.dto;

import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.zupacademy.gerson.mercadolivre.enumerated.StatusRetorno;
import br.com.zupacademy.gerson.mercadolivre.modelo.Compra;
import br.com.zupacademy.gerson.mercadolivre.modelo.Transacao;

public class RetornoPagamento {

	@NotBlank
	private String idTransacao;
	@Enumerated
	@NotNull
	private StatusRetorno statusPagamento;

	public RetornoPagamento(@NotBlank String idTransacao, @NotBlank String status) {
		super();
		this.idTransacao = idTransacao;
		if (status.equals("1")) {
			this.statusPagamento = StatusRetorno.SUCESSO;

		} else if (status.equals("0")) {
			this.statusPagamento = StatusRetorno.ERRO;
		} else {
			this.statusPagamento = this.statusPagamento.valueOf(status);

		}
	}

	public String getIdTransacao() {
		return idTransacao;
	}

	public StatusRetorno getStatusPagamento() {
		return statusPagamento;
	}

	public Transacao toTrasacao(Compra compra) {

		return new Transacao(idTransacao, statusPagamento, compra);
	}

}
