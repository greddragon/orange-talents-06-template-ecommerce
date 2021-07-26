package br.com.zupacademy.gerson.mercadolivre.dto;

import java.math.BigDecimal;
import java.util.Set;
import java.util.stream.Collectors;

import br.com.zupacademy.gerson.mercadolivre.modelo.Opiniao;
import br.com.zupacademy.gerson.mercadolivre.modelo.Produto;

public class ProdutoDetalhesDto {

	private String nome;
	private BigDecimal preco;
	private String descricao;
	private Set<ProdutoCaracteristicasDto> caracteristicas;
	private Set<String> imagens;
	private Set<String> perguntas;
	private Set<opiniaoDto> opinioes;
	private double media_notas;
	private int total_notas = 0;

	public ProdutoDetalhesDto(Produto produto) {
		this.nome = produto.getNome();
		this.preco = produto.getValor();
		this.descricao = produto.getDescricao();
		this.caracteristicas = produto.toCaracteristicasDetalhes();
		this.imagens = produto.toImagensDetalhes();
		this.perguntas = produto.toPerguntasDetalhes();
		this.opinioes = produto.toOpinioesDetalhes();
		
		this.media_notas = Opiniao.toMediaNotas(this.opinioes);
		
		Set<Integer> notas = opinioes.stream().map(opiniao -> opiniao.getNota()).collect(Collectors.toSet());
		
		notas.stream().forEach( nota -> {
				this.total_notas++;
		});
	}

	public String getNome() {
		return nome;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public String getDescricao() {
		return descricao;
	}

	public Set<ProdutoCaracteristicasDto> getCaracteristicas() {
		return caracteristicas;
	}

	public Set<String> getImagens() {
		return imagens;
	}

	public Set<String> getPerguntas() {
		return perguntas;
	}

	public Set<opiniaoDto> getOpinioes() {
		return opinioes;
	}

	public double getMedia_notas() {
		return media_notas;
	}

	public int getTotal_notas() {
		return total_notas;
	}
	
	
	
	

}
