package br.com.zupacademy.gerson.mercadolivre.controller;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.gerson.mercadolivre.dto.NotaFiscalDto;
import br.com.zupacademy.gerson.mercadolivre.dto.RankingDto;

@RestController
public class CompraConcluidaComSucessoFakeController {
	
	
	@PostMapping(value = "/nota-fiscal")
	public void notaFiscal(@RequestBody @Valid NotaFiscalDto notaFiscal)  {
		
		System.out.println(notaFiscal.toString());
		
		//Thread.sleep(150);
		
	}
	@PostMapping(value = "/ranking")
	public void ranking (@RequestBody @Valid RankingDto ranking) {
		
		System.out.println(ranking.toString());
		
		//Thread.sleep(150);
		
	}
}
