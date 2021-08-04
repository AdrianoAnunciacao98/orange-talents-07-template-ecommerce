package br.com.zupacademy.adriano.mercadolivre.controllers.validator;

import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class UniqueValueValidator implements ConstraintValidator<UniqueValue, Object> {
    private String field;
    private Class<?> table;
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void initialize(UniqueValue constraintAnnotation) {
        this.table = constraintAnnotation.table();
        this.field = constraintAnnotation.field();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {

        if(value == null){
            return true;
        }

        Query query = entityManager.createQuery("select 1 from " + table.getName() + " where "+ field + " = :value")
                .setParameter("value", value);

        List<?> list = query.getResultList();


        return list.isEmpty();
    }
}