package br.com.zupacademy.adriano.mercadolivre.controllers.validator;

import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class ExistsIdValidator implements ConstraintValidator<ExistsId, Long> {

    private String domainAttribute;
    private Class<?> klass;

    @PersistenceContext
    private EntityManager manager;

    @Override
    public void initialize(ExistsId params) {
       domainAttribute = params.fieldName();
       klass = params.domainClass();
    }

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        Query query = manager.createQuery("select 1 from " + klass.getName() + " where "+ domainAttribute + " = :value")
                .setParameter("value", value);

        List<?> list = query.getResultList();
        Assert.isTrue(list.size() <=1, "aconteceu algo bizarro e voce tem mais de um" + klass+
                "com o atributo" + domainAttribute + "com o valor = " + value);
        return !query.getResultList().isEmpty();
    }
}
