package br.com.zupacademy.gerson.mercadolivre.modelo;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
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
import br.com.zupacademy.gerson.mercadolivre.dto.opiniaoDto;

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

	@OneToMany(mappedBy = "produto", cascade = CascadeType.PERSIST)
	private Set<ProdutoCaracteristica> caracteristicas = new HashSet<>();
	
	@OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE)
	private Set<ProdutoImagem> imagens = new HashSet<>();
	
	@OneToMany(mappedBy = "produto")
	private Set<Pergunta> perguntas = new HashSet<>();
	
	@OneToMany(mappedBy = "produto")
	private Set<Opiniao> opinioes = new HashSet<>();
	
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
				.map(caracteristica -> caracteristica.toCaracteristica(this)).collect(Collectors.toSet()));
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

	public Set<ProdutoCaracteristica> getCaracteristicas() {
		return caracteristicas;
	}


	public void AssociaImagens(Set<String> links) {
		
		Set<ProdutoImagem> imagens = links.stream().map(link -> new ProdutoImagem(this, link)).collect(Collectors.toSet());
		
		this.imagens.addAll(imagens);
	}

	public Set<ProdutoCaracteristicasDto> toCaracteristicasDetalhes() {
		
		return caracteristicas.stream().map(caracteristica -> 
				new ProdutoCaracteristicasDto(caracteristica.getNome(), caracteristica.getDescricao()))
				.collect(Collectors.toSet());
	}

	public Set<String> toImagensDetalhes() {

		return imagens.stream().map(imagem -> imagem.getLink()).collect(Collectors.toSet());
	}

	public Set<String> toPerguntasDetalhes() {
		
		return perguntas.stream().map(pergunta -> pergunta.getTitulo()).collect(Collectors.toSet());
	}

	public Set<opiniaoDto> toOpinioesDetalhes() {
		
		return opinioes.stream().map(opiniao -> new opiniaoDto(opiniao.getTitulo(), 
				opiniao.getNota(), opiniao.getDescricao())).collect(Collectors.toSet());
	}

	public Boolean AbaterQuantidade(int quantidade2) {
		
		if(this.quantidade >= quantidade2) {
			this.quantidade -= quantidade2;
			return true;
		}
		return false;
	}

}
