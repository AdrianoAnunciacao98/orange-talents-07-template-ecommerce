package br.com.zupacademy.adriano.mercadolivre.controllers.validator;

import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class UniqueValueValidator implements ConstraintValidator<UniqueValue, Object> {
    private Class<?> klass;
    private String domainAttribute;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void initialize(UniqueValue params) {
        domainAttribute = params.fieldName();
        klass = params.domainClass();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {

        Query query = entityManager.createQuery("select 1 from " + klass.getName() + " where "+ domainAttribute + " = :value")
                .setParameter("value", value);
        List<?> list = query.getResultList();
        Assert.isTrue(list.size() <=1, "Foi encontrado mais de um" +klass+ "com o atributo" + domainAttribute+ "= " + value);

        return list.isEmpty();
    }
}