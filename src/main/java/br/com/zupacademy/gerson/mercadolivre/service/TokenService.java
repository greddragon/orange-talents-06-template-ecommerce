package br.com.zupacademy.gerson.mercadolivre.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.com.zupacademy.gerson.mercadolivre.modelo.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {

	@Value("${jwt.expiration}")
	private String expiration;

	@Value("${jwt.secret}")
	private String secret;

	public String geradorToken(Authentication authentication) {
		
		Usuario usuario = (Usuario) authentication.getPrincipal();
		
		Date dataGerado = new Date();
		Date dataExpiracao = new Date(dataGerado.getTime() + Long.parseLong(expiration));
		
		return Jwts.builder().setIssuer("Mercado Livre")
							 .setSubject(usuario.getId().toString())
							 .setIssuedAt(dataGerado)
							 .setExpiration(dataExpiracao)
							 .signWith(SignatureAlgorithm.HS256, secret).compact();
	}

	public boolean isValid(String token) {
		
		try {
			Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}

	}

	public Long getIdToken(String token) {
		
		Claims body = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
		return Long.parseLong(body.getSubject());
	}

}
