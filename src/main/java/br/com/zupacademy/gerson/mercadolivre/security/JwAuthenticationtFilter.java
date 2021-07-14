package br.com.zupacademy.gerson.mercadolivre.security;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.zupacademy.gerson.mercadolivre.modelo.Usuario;
import br.com.zupacademy.gerson.mercadolivre.repository.UsuarioRepository;
import br.com.zupacademy.gerson.mercadolivre.service.TokenService;

@Component
public class JwAuthenticationtFilter extends OncePerRequestFilter {
	
	
	private TokenService tokenService;
	
	
	private UsuarioRepository repository;
	
	

	public JwAuthenticationtFilter(TokenService tokenService, UsuarioRepository repository) {
		super();
		this.tokenService = tokenService;
		this.repository = repository;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		

		String token = getToken(request);
		
		boolean valid = tokenService.isValid(token);
		
		if (valid) {
			thenticationToken(token);
		}
		
		
		
		filterChain.doFilter(request, response);

	}

	private void thenticationToken(String token) {
		Long id = tokenService.getIdToken(token);

		Optional<Usuario> usuarioPresent = repository.findById(id);

		if (usuarioPresent.isPresent()) {

			Usuario usuario = usuarioPresent.get();

			UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(usuario, null,
					usuario.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(auth);
		}
		
	}

	private String getToken(HttpServletRequest request) {
		
		String token = request.getHeader("Authorization");
		if(token == null || token.isEmpty()|| !token.startsWith("Bearer ")) {
			return null;
		}

		return token.substring(7, token.length());

	}

}
