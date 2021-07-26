package br.com.zupacademy.gerson.mercadolivre.modelo;

import java.util.OptionalDouble;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

import br.com.zupacademy.gerson.mercadolivre.dto.opiniaoDto;

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
	
	@Deprecated
	public Opiniao() {
	
	}

	public Opiniao(@NotBlank String titulo, @Min(1) @Max(5) int nota, @Length(max = 500) String descricao,
			@NotNull Produto produto, @NotNull Usuario usuario) {
		super();
		this.titulo = titulo;
		this.nota = nota;
		this.descricao = descricao;
		this.produto = produto;
		this.usuario = usuario;
	}

	public String getTitulo() {
		return titulo;
	}

	public int getNota() {
		return nota;
	}

	public String getDescricao() {
		return descricao;
	}

	public static double toMediaNotas(Set<opiniaoDto> opinioes) {
		Set<Integer> notas = opinioes.stream().map(opiniao -> opiniao.getNota()).collect(Collectors.toSet());
		IntStream mapMedia = notas.stream().mapToInt(nota -> nota);
		OptionalDouble media = mapMedia.average();
		if(media.isPresent()) {
			return media.getAsDouble();
		}
		return 0;
	}
	

}
