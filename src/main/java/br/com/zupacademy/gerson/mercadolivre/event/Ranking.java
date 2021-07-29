package br.com.zupacademy.gerson.mercadolivre.event;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import br.com.zupacademy.gerson.mercadolivre.modelo.Compra;

@Service
public class Ranking implements EventosDeSucesso{

	@Override
	public void realizaEvento(Compra compra, HttpServletRequest request) {
		
		String token = request.getHeader("Authorization");
		
		Map<String, Object> corpo = Map.of("id_compra", compra.getId(),
				"id_vendedor", compra.getProduto().getUsuario().getId());
		
		WebClient client = WebClient.create();
		
		String response = client.post().uri("http://localhost:8080/ranking")
				.header("Authorization", token)
				.contentType(MediaType.APPLICATION_JSON)
				.body(BodyInserters.fromValue(corpo))
				.retrieve()
				.bodyToMono(String.class).block();
		
	}

}
