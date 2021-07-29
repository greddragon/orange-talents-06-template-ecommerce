package br.com.zupacademy.gerson.mercadolivre.service;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zupacademy.gerson.mercadolivre.enumerated.Gateaway;
import br.com.zupacademy.gerson.mercadolivre.modelo.Compra;

@Service
public class MontarLink {
	
	
	
	public String uri(Compra compra, UriComponentsBuilder uriComponents) throws URISyntaxException {
		
		if (compra.getGateaway().equals(Gateaway.PAYPAL)) {

			String uri = montarURI(uriComponents, compra.getId(), "paypal", "paypal.com");
			return uri;

		} else {

			String uri = montarURI(uriComponents,compra.getId(), "pagseguro", "pagseguro.com");
			return uri;
		}
	
	}
	
	private String montarURI(UriComponentsBuilder uriComponents, Long id, String gateaway, String siteGateaway)
			throws URISyntaxException {

		String path = uriComponents.path("app-pagamento-" + gateaway + "/{id}").buildAndExpand(id).toString();
		URI uri = new URI(siteGateaway + "?buyerId=" + id + "&redirectUrl=");

		return uri + path;
	}
}
