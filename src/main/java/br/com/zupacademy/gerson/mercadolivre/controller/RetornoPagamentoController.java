package br.com.zupacademy.gerson.mercadolivre.controller;

import java.net.URISyntaxException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zupacademy.gerson.mercadolivre.dto.RetornoPagamento;
import br.com.zupacademy.gerson.mercadolivre.event.NotaFiscal;
import br.com.zupacademy.gerson.mercadolivre.modelo.Compra;
import br.com.zupacademy.gerson.mercadolivre.service.ChamaEventoService;

@RestController
public class RetornoPagamentoController {

	@PersistenceContext
	EntityManager em;

	@Autowired
	ChamaEventoService eventoService;



	@PostMapping(value = { "app-pagamento-paypal/{id}", "app-pagamento-pagseguro/{id}" })
	@Transactional
	public ResponseEntity<?> processaPagamento(@PathVariable("id") Long id, @RequestBody RetornoPagamento pag,
			HttpServletRequest request, UriComponentsBuilder uriComponents) throws URISyntaxException {

		Compra compra = em.find(Compra.class, id);

		compra.realizarPagamento(pag);
		em.merge(compra);
		eventoService.RealizaEvento(compra, request, uriComponents);
		
		//nota.realizaEvento(compra,request);
		return ResponseEntity.ok().body(pag.getStatusPagamento());
	}
}
