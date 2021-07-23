package br.com.zupacademy.gerson.mercadolivre.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class Upload {

	public Set<String> enviar(List<MultipartFile> imagens) {

		return imagens.stream().map(imagem -> "http://banco-imagem.io/" + imagem.getOriginalFilename().replace(" ", ""))
				.collect(Collectors.toSet());
	}

}
