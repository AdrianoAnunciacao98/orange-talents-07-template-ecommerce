package br.com.zupacademy.adriano.mercadolivre.controllers.dto;


import br.com.zupacademy.adriano.mercadolivre.entidades.Usuario;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;
import java.time.Instant;
import java.util.Date;

public class UsuarioDto {

    @NotBlank
    private String login;

    @NotBlank(message = "minimo de 6 caracteres")
    @Size(min=6)
    private String senha;

    @Past(message = "A data não pode ser futura")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "pt-BR", timezone = "Brazil/East")
    private Date data;

    public UsuarioDto(String login, String senha, Date data) {
        this.login = login;
        this.senha = senha;
        this.data = data;
    }

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }

    public Date getData() {
        return data;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Deprecated
    public UsuarioDto(){}

public Usuario toModel(){
    return new Usuario(this.login = login, this.senha = senha, this.data = data);
}
}
