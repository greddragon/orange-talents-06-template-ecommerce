package br.com.zupacademy.gerson.mercadolivre.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

@Entity
public class Categoria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	private String nome;

	@ManyToOne
	private Categoria Categoria_mae;
	
	@Deprecated
	public Categoria() {}

	public Categoria(@NotBlank String nome, Categoria categoria_mae) {
		super();
		this.nome = nome;
		Categoria_mae = categoria_mae;
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public Categoria getCategoria_mae() {
		return Categoria_mae;
	}
	
	
	
	

}
