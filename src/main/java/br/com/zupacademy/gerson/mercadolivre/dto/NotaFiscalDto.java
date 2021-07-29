package br.com.zupacademy.gerson.mercadolivre.dto;

import javax.validation.constraints.NotNull;

public class NotaFiscalDto {

	@NotNull
	private Long id_compra;
	@NotNull
	private Long id_usuario;

	public NotaFiscalDto(@NotNull Long id_compra, @NotNull Long id_usuario) {
		super();
		this.id_compra = id_compra;
		this.id_usuario = id_usuario;
	}

	public Long getId_compra() {
		return id_compra;
	}

	public Long getId_usuario() {
		return id_usuario;
	}

	@Override
	public String toString() {
		return "NotaFiscalDto [id_compra=" + id_compra + ", id_usuario=" + id_usuario + "]";
	}

}
