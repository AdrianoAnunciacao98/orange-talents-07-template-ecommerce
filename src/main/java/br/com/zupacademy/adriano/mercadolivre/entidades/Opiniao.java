package br.com.zupacademy.adriano.mercadolivre.entidades;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;

@Entity
public class Opiniao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Min(1)
    @Max(5)
    private int nota;
    @NotBlank
    private String titulo;

    @NotBlank
    @Size(max=500)
    private String descricao;

    @NotNull @Valid
    @ManyToOne
    private Produtos produto;

    @NotNull @Valid
    @ManyToOne
    private Usuario consumidor;

    public Opiniao(int nota, String titulo, String descricao, Produtos produto, Usuario consumidor) {
        this.nota = nota;
        this.titulo = titulo;
        this.descricao = descricao;
        this.produto = produto;
        this.consumidor = consumidor;
    }

    @Override
    public String toString() {
        return "Opiniao{" +
                "nota=" + nota +
                ", titulo='" + titulo + '\'' +
                ", descricao='" + descricao + '\'' +
                ", produto=" + produto +
                ", consumidor=" + consumidor +
                '}';
    }

    public int getNota() {
        return nota;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }
}
