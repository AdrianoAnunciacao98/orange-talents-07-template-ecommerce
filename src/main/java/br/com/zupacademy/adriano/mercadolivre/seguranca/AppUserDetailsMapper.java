package br.com.zupacademy.adriano.mercadolivre.seguranca;

import br.com.zupacademy.adriano.mercadolivre.entidades.Usuario;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;

@Configuration
public class AppUserDetailsMapper implements UserDetailsMapper{

    //objeto para ser logado.
    @Override
    public UserDetails map(Object shouldBeASystemUser) {
        return new UsuarioLogado((Usuario) shouldBeASystemUser);
    }
}
