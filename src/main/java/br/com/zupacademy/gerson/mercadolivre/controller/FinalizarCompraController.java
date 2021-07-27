package br.com.zupacademy.gerson.mercadolivre.controller;

import java.net.URI;
import java.net.URISyntaxException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zupacademy.gerson.mercadolivre.dto.compraDto;
import br.com.zupacademy.gerson.mercadolivre.enumerated.Gateaway;
import br.com.zupacademy.gerson.mercadolivre.modelo.Compra;
import br.com.zupacademy.gerson.mercadolivre.modelo.Produto;
import br.com.zupacademy.gerson.mercadolivre.modelo.Usuario;

@RestController
@RequestMapping("/compras")
public class FinalizarCompraController {

	@PersistenceContext
	EntityManager em;

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

			if (compra.getGateaway().equals(Gateaway.PAYPAL)) {

				String uri = montarURI(uriComponents, compra.getId(), "paypal", "paypal.com");
				return ResponseEntity.ok().body(uri);

			} else {

				String uri = montarURI(uriComponents, compra.getId(), "pagseguro", "pagseguro.com");
				return ResponseEntity.ok().body(uri);
			}

		}

		BindException semEstoque = new BindException(compraDto, "CompraDto");
		semEstoque.reject(null, "O produto está sem estoque.");

		throw semEstoque;

	}

	private String montarURI(UriComponentsBuilder uriComponents, Long id, String gateaway, String siteGateaway)
			throws URISyntaxException {

		String path = uriComponents.path("app-pagamento-" + gateaway + "/{id}").buildAndExpand(id).toString();
		URI uri = new URI(siteGateaway + "?buyerId=" + id + "&redirectUrl=");

		return uri + path;
	}

}
