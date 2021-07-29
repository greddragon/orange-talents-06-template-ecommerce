package br.com.zupacademy.gerson.mercadolivre.event;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import br.com.zupacademy.gerson.mercadolivre.modelo.Compra;

@Service
public class EmailSucesso implements EventosDeSucesso{
	
	@Autowired
	private JavaMailSender mailSender;

	
	@Override
	public void realizaEvento(Compra compra, HttpServletRequest request) {
		
		SimpleMailMessage email = new SimpleMailMessage();
		
		String mensagem = "\nCompra realizada com sucesso."
				+ " \nProduto: " + compra.getProduto().getNome() + " \nQuantidade: " + compra.getQuantidade()
				+ " \npreço do produto: " + compra.getPrecoMomento() + " \nForma de Pagamento: " + compra.getGateaway();
		
		
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
