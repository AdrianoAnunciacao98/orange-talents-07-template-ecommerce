package br.com.zupacademy.adriano.mercadolivre.seguranca;

import org.springframework.security.core.userdetails.UserDetails;

public interface UserDetailsMapper {

    UserDetails map(Object shouldBeASystemUser);
}
