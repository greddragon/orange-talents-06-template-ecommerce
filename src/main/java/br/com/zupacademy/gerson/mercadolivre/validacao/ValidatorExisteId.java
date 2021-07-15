package br.com.zupacademy.gerson.mercadolivre.validacao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.util.Assert;

public class ValidatorExisteId implements ConstraintValidator<ExisteId, Object> {

	String valorAtributo;
	Class<?> classe;

	@PersistenceContext
	EntityManager em;

	@Override
	public void initialize(ExisteId valoresRecebidos) {
		this.valorAtributo = valoresRecebidos.value();
		this.classe = valoresRecebidos.classe();

	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {

		if (value == null) {
			return true;
		}

		Query query = em.createQuery("SELECT 1 FROM " + classe.getName() + " WHERE " + valorAtributo + " =:valor");
		query.setParameter("valor", value);
		List<?> lista = query.getResultList();
		// Object entidade = em.find(classe.getClass(), value);
		// Assert.state(entidade != null,
		// "Id da " + classe.getName() + "não existe no sistema");
		// return entidade == null;
		Assert.state(lista.size() <= 1,
				valorAtributo + " " + classe + "já cadastrado no sistema, não pode haver mais de um");
		return !lista.isEmpty();
	}

}
