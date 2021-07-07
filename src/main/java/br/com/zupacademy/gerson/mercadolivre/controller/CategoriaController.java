package br.com.zupacademy.gerson.mercadolivre.controller;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.gerson.mercadolivre.dto.CategoriaDto;
import br.com.zupacademy.gerson.mercadolivre.modelo.Categoria;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {

	@PersistenceContext
	EntityManager em;

	@PostMapping(value = ("/cadastro"))
	@Transactional
	public ResponseEntity<?> cadastro(@RequestBody @Valid CategoriaDto request) {
		Categoria categoria = request.toCategoria(em);
		em.persist(categoria);
		return ResponseEntity.ok().build();
	}
}
