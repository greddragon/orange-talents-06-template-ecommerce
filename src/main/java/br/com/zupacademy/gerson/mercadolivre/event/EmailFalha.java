package br.com.zupacademy.gerson.mercadolivre.event;

import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zupacademy.gerson.mercadolivre.modelo.Compra;
import br.com.zupacademy.gerson.mercadolivre.service.MontarLink;

@Service
public class EmailFalha {
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private MontarLink montarlink;

	public void email(Compra compra, UriComponentsBuilder uriComponents) throws URISyntaxException {
		
		SimpleMailMessage email = new SimpleMailMessage();
		
		String link = montarlink.uri(compra, uriComponents);
		
		String mensagem = "\nPagamento falhou."
				+ " \nProduto: " + compra.getProduto().getNome() + " \nQuantidade: " + compra.getQuantidade()
				+ " \npreço do produto: " + compra.getPrecoMomento() + " \nForma de Pagamento: " + compra.getGateaway()
				+ " \nLink para refazer o pagamento: " + link;
		
		
		/* para enviar o email em produção
		 * Para o envio do email, eu fiz o exemplo via  SMTP do Gmail.
		 * Eu comentei a chamada abaixa para poder continuar testanto
		 * sem que fique enviando mensagem para  o email.
		 * Um outro detalhe é que remove o meu email e senha das configurações do apllication.properties
		 * então para testar o envio é preciso colocar o email e a senha, caso o envio não funcione
		 * é porque o email em questão precisa prescisa ser configurado o SMTP no email.
		 */
		
		/*
		 * email.setTo(); 
		 * email.setSubject("Compra realizada com sucesso");
		 * email.setText(mensagem); 
		 * mailSender.send(email);
		 */
		
		System.out.println(mensagem);

		
	}
}
