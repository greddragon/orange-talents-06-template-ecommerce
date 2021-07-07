package br.com.zupacademy.gerson.mercadolivre.dto;

public class ErrorDto {
	private String campo;
	private String erro;

	public ErrorDto(String campo, String erro) {
		super();
		this.campo = campo;
		this.erro = erro;
	}

	public String getCampo() {
		return campo;
	}

	public String getErro() {
		return erro;
	}

}
