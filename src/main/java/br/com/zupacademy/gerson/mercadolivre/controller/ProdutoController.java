package br.com.zupacademy.gerson.mercadolivre.controller;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.gerson.mercadolivre.dto.ProdutoDto;
import br.com.zupacademy.gerson.mercadolivre.modelo.Produto;
import br.com.zupacademy.gerson.mercadolivre.service.TokenService;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

	@PersistenceContext
	private EntityManager em;

	@Autowired
	private TokenService tokenService;

	@PostMapping(value = "/cadastro")
	@Transactional
	public ResponseEntity<?> cadastrar(@RequestBody @Valid ProdutoDto produtoDto, HttpServletRequest request) {

		Produto produto = produtoDto.getProduto(request, tokenService, em);

		em.persist(produto);

		return ResponseEntity.ok().build();

	}

}
