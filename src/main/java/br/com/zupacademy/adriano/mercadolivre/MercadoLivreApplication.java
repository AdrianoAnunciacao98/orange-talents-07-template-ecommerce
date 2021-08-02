package br.com.zupacademy.adriano.mercadolivre;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

//estou dizendo para não executar essa classe para validar usuario e senha.
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class MercadoLivreApplication {

	public static void main(String[] args) {
		SpringApplication.run(MercadoLivreApplication.class, args);
	}

	@Bean
	public PasswordEncoder getPasswordEncoder(){
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;


	}
}
