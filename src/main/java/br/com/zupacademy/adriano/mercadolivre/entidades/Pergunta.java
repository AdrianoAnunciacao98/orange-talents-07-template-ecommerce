package br.com.zupacademy.adriano.mercadolivre.entidades;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
public class Pergunta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String titulo;

    @NotNull
    @Valid
    @ManyToOne
    private Usuario cliente;

    @NotNull
    @Valid
    @ManyToOne
    private Produtos produto;

    private LocalDate instante;

    public Pergunta(@NotBlank String titulo,@NotNull @Valid Usuario cliente, @NotNull @Valid Produtos produto) {
        this.titulo = titulo;
        this.cliente = cliente;
        this.produto = produto;
        this.instante = LocalDate.now();
    }

    @Deprecated
    public Pergunta(){}

    @Override
    public String toString() {
        return "Pergunta{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", cliente=" + cliente +
                ", produto=" + produto +
                '}';
    }

    public Usuario getInteressada() {
        return cliente;
    }

    public Usuario getDonoProduto() {
        return produto.getDono();
    }

}
