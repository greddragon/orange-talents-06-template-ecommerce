package br.com.zupacademy.gerson.mercadolivre.controller;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.gerson.mercadolivre.dto.ProdutoDetalhesDto;
import br.com.zupacademy.gerson.mercadolivre.modelo.Produto;

@RestController
@RequestMapping("/produto")
public class ProdutoDetalhesController {
	
	@PersistenceContext
	EntityManager em;
	
	@GetMapping(value = "/{id}/detalhes")
	public ResponseEntity<?> DetalhesProduto(@PathVariable("id") Long id){
		
		Produto produto = em.find(Produto.class, id);
		ProdutoDetalhesDto produtoDetalhes = new ProdutoDetalhesDto(produto);
		
		return ResponseEntity.ok().body(produtoDetalhes);
	}
}
