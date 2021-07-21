package br.com.zupacademy.gerson.mercadolivre.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import br.com.zupacademy.gerson.mercadolivre.modelo.Pergunta;

@Service
public class EnviarEmailService {
	@Autowired
	private JavaMailSender mailSender;

	public void enviar(Pergunta pergunta) {

		SimpleMailMessage email = new SimpleMailMessage();

		email.setTo(pergunta.getProduto().getUsuario().getEmail());
		email.setSubject("Teste envio de e-mail");
		email.setText("Pergunta: " + pergunta.getTitulo() + ". Enviado por: " + pergunta.getUsuario().getEmail());
		mailSender.send(email);
	}
}
