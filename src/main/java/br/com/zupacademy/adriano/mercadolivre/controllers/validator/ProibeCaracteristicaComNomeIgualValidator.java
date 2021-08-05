package br.com.zupacademy.adriano.mercadolivre.controllers.validator;

import br.com.zupacademy.adriano.mercadolivre.controllers.dto.ProdutosDto;
import br.com.zupacademy.adriano.mercadolivre.controllers.dto.UsuarioDto;
import br.com.zupacademy.adriano.mercadolivre.entidades.Produtos;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Set;

public class ProibeCaracteristicaComNomeIgualValidator implements Validator {


    @PersistenceContext
    private EntityManager manager;

    @Override
    public boolean supports(Class<?> clazz) {
        return ProdutosDto.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if(errors.hasErrors()){
            return;
        }
       ProdutosDto dto = (ProdutosDto) target;
        Set<String> nomesIguais = dto.buscaCaracteristicasIguais();
        if(!nomesIguais.isEmpty()){
            errors.rejectValue("caracteristicas", null, "Caracteristicas idênticas");
        }
} }
