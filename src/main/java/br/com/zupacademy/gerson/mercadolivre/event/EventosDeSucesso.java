package br.com.zupacademy.gerson.mercadolivre.event;

import javax.servlet.http.HttpServletRequest;

import br.com.zupacademy.gerson.mercadolivre.modelo.Compra;

public interface EventosDeSucesso {
	
	public void realizaEvento(Compra compra, HttpServletRequest request);
}
