package br.com.zupacademy.gerson.mercadolivre.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.gerson.mercadolivre.dto.LoginDto;
import br.com.zupacademy.gerson.mercadolivre.dto.TokenDto;
import br.com.zupacademy.gerson.mercadolivre.service.TokenService;

@RestController
@RequestMapping(value = ("/auth"))
public class AuthenticationController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private TokenService tokenService;
	
	@PostMapping
	public ResponseEntity<TokenDto> auth(@RequestBody @Valid LoginDto request) {
		
		UsernamePasswordAuthenticationToken dados = request.toDadosToken();
		
		
		try {
			Authentication authentication = authenticationManager.authenticate(dados);
			String token = tokenService.geradorToken(authentication);
			System.out.println(token);
			return ResponseEntity.ok().body(new TokenDto("Bearer", token));
			
		} catch (AuthenticationException e) {
			
			return ResponseEntity.badRequest().build();
		}
		
	}

}
