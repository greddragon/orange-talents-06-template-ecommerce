package br.com.zupacademy.gerson.mercadolivre.controller;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.gerson.mercadolivre.dto.opiniaoDto;
import br.com.zupacademy.gerson.mercadolivre.modelo.Opiniao;
import br.com.zupacademy.gerson.mercadolivre.service.TokenService;

@RestController
@RequestMapping("/opiniao")
public class OpiniaoController {

	@PersistenceContext
	EntityManager em;

	@Autowired
	TokenService tokenService;

	@PostMapping("/{id}/cadastro")
	@Transactional
	public ResponseEntity<?> cadastrarOpiniao(@PathVariable("id") Long id, @RequestBody @Valid opiniaoDto opiniaoDto,
			HttpServletRequest request) {

		Opiniao opiniao = opiniaoDto.getOpiniao(id, request, em, tokenService);

		em.persist(opiniao);

		return ResponseEntity.ok().build();
	}
}
