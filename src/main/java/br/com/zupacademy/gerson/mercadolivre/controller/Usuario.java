package br.com.zupacademy.gerson.mercadolivre.controller;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Email
	@NotBlank
	private String email;
	@NotBlank
	@Length(min = 6)
	private String senha;

	public Usuario(@Email @NotBlank String email, @NotBlank @Length(min = 6) String senha) {
		super();
		this.email = email;
		this.senha = new BCryptPasswordEncoder().encode(senha);
	}

}
