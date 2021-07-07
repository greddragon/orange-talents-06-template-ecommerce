package br.com.zupacademy.gerson.mercadolivre.dto;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;

import br.com.zupacademy.gerson.mercadolivre.modelo.Categoria;
import br.com.zupacademy.gerson.mercadolivre.validacao.ValorUnico;

public class CategoriaDto {
	@NotBlank
	@ValorUnico(classe = Categoria.class, value = "nome")
	private String nome;
	private Long id_categoriaMae;

	public CategoriaDto(@NotBlank String nome, Long id_categoriaMae) {
		super();
		this.nome = nome;
		this.id_categoriaMae = id_categoriaMae;
	}

	public String getNome() {
		return nome;
	}

	public Long getId_categoriaMae() {
		return id_categoriaMae;
	}

	public Categoria toCategoria(EntityManager em) {
		Categoria c_mae = null;
		if (id_categoriaMae != null) {
			c_mae = (Categoria) em.createQuery("SELECT c FROM Categoria c").getSingleResult();
		}

		return new Categoria(nome, c_mae);
	}

}
