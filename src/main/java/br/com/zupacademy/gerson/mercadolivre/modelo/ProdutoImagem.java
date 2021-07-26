package br.com.zupacademy.gerson.mercadolivre.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.URL;

@Entity
public class ProdutoImagem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	@NotNull
	@Valid
	private Produto produto;
	@URL
	@NotBlank
	private String link;

	@Deprecated
	public ProdutoImagem() {
		super();
	}

	public ProdutoImagem(@NotNull @Valid Produto produto, @URL @NotBlank String link) {
		super();
		this.produto = produto;
		this.link = link;
	}

	public String getLink() {
		return link;
	}
	
	

}
