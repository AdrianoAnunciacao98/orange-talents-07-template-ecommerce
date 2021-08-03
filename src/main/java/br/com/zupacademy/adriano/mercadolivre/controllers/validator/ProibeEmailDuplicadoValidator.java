package br.com.zupacademy.adriano.mercadolivre.controllers.validator;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.swing.text.html.parser.Entity;
import javax.validation.ConstraintViolation;
import java.util.List;
import java.util.Set;

import br.com.zupacademy.adriano.mercadolivre.controllers.dto.UsuarioDto;
import br.com.zupacademy.adriano.mercadolivre.entidades.Usuario;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ProibeEmailDuplicadoValidator implements Validator {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public boolean supports(Class<?> clazz) {
        return UsuarioDto.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if(errors.hasErrors()){
            return;
        }
        UsuarioDto dto = (UsuarioDto) target;
       Query consultaEmailUnico =  manager.createQuery("select 1 from Usuario u where u.login = :login")
               .setParameter("login", dto.getLogin());
      List<?> resultList =  consultaEmailUnico.getResultList();

      if(!resultList.isEmpty()){
          errors.rejectValue("login", null, "já existe este email no sistema");
      }
    }
}
