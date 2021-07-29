package br.com.zupacademy.gerson.mercadolivre.service;

import java.net.URISyntaxException;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zupacademy.gerson.mercadolivre.event.EmailFalha;
import br.com.zupacademy.gerson.mercadolivre.event.EventosDeSucesso;
import br.com.zupacademy.gerson.mercadolivre.modelo.Compra;

@Service
public class ChamaEventoService {
	
	@Autowired
	private Set<EventosDeSucesso> eventos;
	
	@Autowired
	private EmailFalha emailFalha;
	
	public void RealizaEvento(Compra compra, HttpServletRequest request, UriComponentsBuilder uriComponents) throws URISyntaxException {
		
		if(compra.processadasComSucesso()) {
			//eventos.stream().forEach(evento -> System.out.print(evento));
			eventos.stream().forEach(evento -> evento.realizaEvento(compra, request));
		}else {
			
			emailFalha.email(compra, uriComponents);
		}
		



		
	}

}
