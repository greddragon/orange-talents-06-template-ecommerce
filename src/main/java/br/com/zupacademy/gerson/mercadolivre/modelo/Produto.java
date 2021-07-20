package br.com.zupacademy.gerson.mercadolivre.modelo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.hibernate.validator.constraints.Length;

import br.com.zupacademy.gerson.mercadolivre.dto.ProdutoCaracteristicasDto;

@Entity
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	private String nome;
	@NotNull
	@Positive
	private BigDecimal valor;
	@NotNull
	@Positive
	private int quantidade;
	@NotBlank
	@Length(max = 1000)
	private String descricao;

	@ManyToOne
	private Categoria categoria;

	@ManyToOne
	private Usuario usuario;

	@OneToMany(mappedBy = "produto", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	private List<ProdutoCaracteristica> caracteristicas = new ArrayList<>();
	
	@Deprecated
	public Produto() {

	}
	
	public Produto(@NotBlank String nome, @NotNull @Positive BigDecimal valor, @NotNull @Positive int quantidade,
			@NotBlank @Length(max = 1000) String descricao, Categoria categoria, Usuario usuario,
			Collection<ProdutoCaracteristicasDto> caracteristicas) {
		super();
		this.nome = nome;
		this.valor = valor;
		this.quantidade = quantidade;
		this.descricao = descricao;
		this.categoria = categoria;
		this.usuario = usuario;
		this.caracteristicas.addAll(caracteristicas.stream()
				.map(caracteristica -> caracteristica.toCaracteristica(this)).collect(Collectors.toList()));
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public String getDescricao() {
		return descricao;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public List<ProdutoCaracteristica> getCaracteristicas() {
		return caracteristicas;
	}

}
