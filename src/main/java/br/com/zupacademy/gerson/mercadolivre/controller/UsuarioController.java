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

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
	
	@PersistenceContext
	EntityManager em;
	
	@PostMapping(value = "/novo-cadastro")
	@Transactional
	public ResponseEntity<?> Cadastro(@RequestBody @Valid UsuarioDto request) {
		Usuario usuario = request.toUsuario();
		em.persist(usuario);
		return ResponseEntity.ok().build();
	}
}
