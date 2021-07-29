package br.com.zupacademy.gerson.mercadolivre.controller;

import java.net.URISyntaxException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zupacademy.gerson.mercadolivre.dto.compraDto;
import br.com.zupacademy.gerson.mercadolivre.modelo.Compra;
import br.com.zupacademy.gerson.mercadolivre.modelo.Produto;
import br.com.zupacademy.gerson.mercadolivre.modelo.Usuario;
import br.com.zupacademy.gerson.mercadolivre.service.MontarLink;

@RestController
@RequestMapping("/compras")
public class FinalizarCompraController {

	@PersistenceContext
	EntityManager em;
	@Autowired
	private MontarLink montarlink;

	@PostMapping
	@Transactional
	public ResponseEntity<?> realizarCompra(@RequestBody @Valid compraDto compraDto,
			@AuthenticationPrincipal Usuario user, UriComponentsBuilder uriComponents)
			throws URISyntaxException, BindException {

		Produto produto = em.find(Produto.class, compraDto.getIdProduto());

		Boolean quantidadeAbatida = produto.AbaterQuantidade(compraDto.getQuantidade());

		if (quantidadeAbatida) {

			Compra compra = compraDto.toCompra(produto, user);
			em.persist(compra);
			String uri = montarlink.uri(compra, uriComponents);
			return ResponseEntity.ok().body(uri);

		}

		BindException semEstoque = new BindException(compraDto, "CompraDto");
		semEstoque.reject(null, "O produto está sem estoque.");

		throw semEstoque;

	}

}
