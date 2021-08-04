package br.com.zupacademy.adriano.mercadolivre.seguranca;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class UsersService implements UserDetailsService {
//essa interface é implementada do spring security.

    @PersistenceContext
    private EntityManager manager;

    //reaproveita a query do application.properties
    @Value("${security.username-query}")
    private String query;

    @Autowired
    private UserDetailsMapper userDetailsMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      List<?> objects = manager.createQuery(query).setParameter("username", username).getResultList();
        //defesa - não retornar mais de um objeto
        Assert.isTrue(objects.size() <=1,
                "[BUG] mais de um autenticável tem o mesmo username" +username
                );
        //se estiver vazio, solta uma exceção
        if(objects.isEmpty()){
            throw new UsernameNotFoundException("Não foi possível encontrar usuário com email" + username);
        }
        //se der certo, cria um objeto
        return userDetailsMapper.map(objects.get(0));
    }
}
