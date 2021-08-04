package br.com.zupacademy.adriano.mercadolivre.seguranca;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private UsersService usersService;

    @Autowired
    private TokenManager tokenManager;

    private static final Logger log = LoggerFactory.getLogger(SecurityConfiguration.class);

    @Override
    //constante do springsecurity que já define como ele é exposto
    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                //esse antmatchers serve para configurar endereços - permitindo todos os acessos
                //permite os acessos dependendo da questão, como o id - de 0 a 9.
                .antMatchers(HttpMethod.GET, "/produtos/{id: [0-9]+}").permitAll()
                .antMatchers(HttpMethod.POST, "/produtos/{id: [0-9]+}").permitAll()
                .antMatchers(HttpMethod.POST, "/api/produtos/{id: [0-9]+}").permitAll()
                .antMatchers("/**")
                .permitAll()
                .antMatchers(HttpMethod.POST, "/usuarios").permitAll()
                //quando a pessoa autenticar, quero retornar o token
                //teria que fazer uma configuração adicional de token
                .antMatchers("/auth/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .cors()
                .and()
                //desabilitando proteção contra um tipo de ataque
                .csrf().disable()
                .sessionManagement()
                //nunca criar uma sessão no servidor, pois autenticação é via token
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                //precisa carregar o token que veio do header, colocar o usuario
                //e fingir que foi autenticado
                .addFilterBefore(new JwtAuthenticationFilter(tokenManager, usersService),
                        UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling()
                .authenticationEntryPoint(new JwtAuthenticationEntryPoint());
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(this.usersService)
                .passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/**.html",  "/v2/api-docs", "/webjars/**",
                "/configuration/**", "/swagger-resources/**", "/css/**", "/**.ico", "/js/**");
    }

    private static class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

        private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationEntryPoint.class);

        @Override
        public void commence(HttpServletRequest request, HttpServletResponse response,
                             AuthenticationException authException) throws IOException, ServletException {

            logger.error("Um acesso não autorizado foi verificado. Mensagem: {}", authException.getMessage());
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Você não está autorizado a acessar esse recurso.");
        } }


}
