package br.com.zupacademy.adriano.mercadolivre.entidades;

import br.com.zupacademy.adriano.mercadolivre.controllers.validator.ExistsId;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Entity
public class Produtos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    @Positive
    private int quantidade;

    @NotBlank
    @Length(max=1000)
    private String descricao;

    @NotNull
    @Positive
    private BigDecimal valor;

    @NotNull
    @ExistsId(field = "id", table = Categoria.class, message = "Essa categoria não existe no sistema")
    @ManyToOne
    private Categoria categoria;
    @NotNull
    @Min(value = 0)
    private Integer quantidadeDisponivel;

    private Instant instanteCadastro;

    @ManyToOne
    @NotNull
    @Valid
    private Usuario dono;

    public Produtos(String nome, int quantidade, String descricao, BigDecimal valor, Categoria categoria, Usuario dono) {
        this.nome = nome;
        this.quantidade = quantidade;
        this.descricao = descricao;
        this.valor = valor;
        this.categoria = categoria;
        this.dono = dono;
    }



    public String getNome() {
        return nome;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public String getDescricao() {
        return descricao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public Usuario getDono() {
        return dono;
    }

    @Deprecated
    public Produtos(){}

    @Override
    public String toString() {
        return "Produtos{" +
                "nome='" + nome + '\'' +
                ", quantidade=" + quantidade +
                ", descricao='" + descricao + '\'' +
                ", valor=" + valor +
                ", categoria=" + categoria +
                ", quantidadeDisponivel=" + quantidadeDisponivel +
                ", instanteCadastro=" + instanteCadastro +
                ", dono=" + dono +
                '}';
    }
}
