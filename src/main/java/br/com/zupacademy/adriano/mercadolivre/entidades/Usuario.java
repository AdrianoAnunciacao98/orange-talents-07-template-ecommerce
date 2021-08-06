package br.com.zupacademy.adriano.mercadolivre.entidades;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(unique = true)
    @Email
    private String login;

    @NotBlank(message = "minimo de 6 caracteres")
    @Size(min=6)
    private String senha;

    @Past(message = "A data não pode ser futura")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss", timezone="GMT-3")
    private Date data;



    public Usuario(String login, SenhaLimpa senhaLimpa, Date data) {

        this.login = login;
        this.senha = senhaLimpa.getSenhaComHash();
        this.data = data;
    }



    public Long getId() {
        return id;
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

    @Deprecated
    public Usuario(){}

    @Override
    public String toString() {
        return "Usuario{" +
                "login='" + login + '\''
               ;
    }

    public String getEmail() {
        return this.login;
    }
}
