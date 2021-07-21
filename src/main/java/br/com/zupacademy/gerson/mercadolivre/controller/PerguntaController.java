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

import br.com.zupacademy.gerson.mercadolivre.dto.PerguntaDto;
import br.com.zupacademy.gerson.mercadolivre.modelo.Pergunta;
import br.com.zupacademy.gerson.mercadolivre.service.EnviarEmailService;
import br.com.zupacademy.gerson.mercadolivre.service.TokenService;

@RestController
@RequestMapping("/pergunta")
public class PerguntaController {

	@PersistenceContext
	EntityManager em;

	@Autowired
	TokenService tokenService;
	
	@Autowired
	EnviarEmailService emailService;

	@PostMapping("/{id}/cadastro")
	@Transactional
	public ResponseEntity<?> cadastrarPergunta(@PathVariable("id") Long id, @RequestBody @Valid PerguntaDto perguntaDto,
			HttpServletRequest request) {
		
		Pergunta pergunta = perguntaDto.getPergunta(id,request, em, tokenService);
		
		em.persist(pergunta);
		
		// Para o envio do email, eu fiz o exemplo via  SMTP do Gmail.
		// Eu comentei a chamada abaixa para poder continuar testanto o cadastro de pergunta
		// sem que fique enviando mensagem para  o email.
		// Um outro detalhe é que remove o meu email e senha das configurações do apllication.properties
		// então para testar o envio é preciso colocar o email e a senha, caso o envio não funcione
		// é porque o email em questão precisa prescisa ser configurado o SMTP no email.
		
		//emailService.enviar(pergunta);
		
		return ResponseEntity.ok().build();
	}
}
