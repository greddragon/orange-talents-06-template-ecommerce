package br.com.zupacademy.gerson.mercadolivre.validacao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.util.Assert;

public class ValidatorValorUnico implements ConstraintValidator<ValorUnico, Object> {

	String valorAtributo;
	Class<?> classe;

	@PersistenceContext
	EntityManager em;

	@Override
	public void initialize(ValorUnico valoresRecebidos) {
		this.valorAtributo = valoresRecebidos.value();
		this.classe = valoresRecebidos.classe();

	}

	@Override
	public boolean isValid(Object obj, ConstraintValidatorContext context) {
		Query query = em.createQuery("SELECT 1 FROM " + classe.getName() + " WHERE " + valorAtributo + " =:valor");
		query.setParameter("valor", obj);
		List<?> lista = query.getResultList();
		Assert.state(lista.size() <= 1,
				valorAtributo + " " + classe + "já cadastrado no sistema, não pode haver mais de um");
		return lista.isEmpty();
	}

}
