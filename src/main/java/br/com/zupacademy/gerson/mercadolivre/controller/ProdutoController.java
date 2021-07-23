package br.com.zupacademy.gerson.mercadolivre.controller;

import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.zupacademy.gerson.mercadolivre.dto.ImagensDto;
import br.com.zupacademy.gerson.mercadolivre.dto.ProdutoDto;
import br.com.zupacademy.gerson.mercadolivre.dto.UsuarioDto;
import br.com.zupacademy.gerson.mercadolivre.modelo.Produto;
import br.com.zupacademy.gerson.mercadolivre.modelo.Usuario;
import br.com.zupacademy.gerson.mercadolivre.service.TokenService;
import br.com.zupacademy.gerson.mercadolivre.service.Upload;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

	@PersistenceContext
	private EntityManager em;

	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private Upload upload;

	@PostMapping(value = "/cadastro")
	@Transactional
	public ResponseEntity<?> cadastrar(@RequestBody @Valid ProdutoDto produtoDto, HttpServletRequest request) {

		Produto produto = produtoDto.getProduto(request, tokenService, em);

		em.persist(produto);

		return ResponseEntity.ok().build();

	}
	
	@PostMapping(value = "/cadastro/{id}/imagens")
	@Transactional
	public void cadastrarImagens(@PathVariable("id") Long id, @Valid ImagensDto imagens, HttpServletRequest request) {
		
		Usuario usuarioLogado = UsuarioDto.getUsuarioLogado(request, tokenService, em);
		
		Produto produto = em.find(Produto.class, id);
		Assert.state(produto != null, "Produto não existe");
		
		
		if(usuarioLogado.getId() != produto.getUsuario().getId()) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		
		Set<String> links = upload.enviar(imagens.getImagens());
		
		System.out.println(links);
		
		
		produto.AssociaImagens(links);
		
		em.merge(produto);
		
	}

}
