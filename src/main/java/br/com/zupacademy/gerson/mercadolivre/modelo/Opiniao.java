package br.com.zupacademy.gerson.mercadolivre.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

@Entity
public class Opiniao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	private String titulo;

	@Min(1)
	@Max(5)
	private int nota;

	@Length(max = 500)
	private String descricao;

	@NotNull
	@ManyToOne
	private Produto produto;

	@NotNull
	@ManyToOne
	private Usuario usuario;

	public Opiniao(@NotBlank String titulo, @Min(1) @Max(5) int nota, @Length(max = 500) String descricao,
			@NotNull Produto produto, @NotNull Usuario usuario) {
		super();
		this.titulo = titulo;
		this.nota = nota;
		this.descricao = descricao;
		this.produto = produto;
		this.usuario = usuario;
	}

}
