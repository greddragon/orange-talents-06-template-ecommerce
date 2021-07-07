
package br.com.zupacademy.gerson.mercadolivre.validacao;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidatorValorUnico.class)
public @interface ValorUnico {

	String message() default "{error.messagem.valorunico}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	String value();

	Class<?> classe();
}
