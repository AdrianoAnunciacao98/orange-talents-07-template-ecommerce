package br.com.zupacademy.adriano.mercadolivre.controllers.dto;

import br.com.zupacademy.adriano.mercadolivre.controllers.validator.ExistsId;
import br.com.zupacademy.adriano.mercadolivre.entidades.Caracteristica;
import br.com.zupacademy.adriano.mercadolivre.entidades.Categoria;
import br.com.zupacademy.adriano.mercadolivre.entidades.Produtos;
import br.com.zupacademy.adriano.mercadolivre.entidades.Usuario;
import br.com.zupacademy.adriano.mercadolivre.repository.UsuarioRepository;
import br.com.zupacademy.adriano.mercadolivre.seguranca.UsuarioLogado;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.parameters.P;

import javax.persistence.EntityManager;
import javax.persistence.ManyToOne;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.List;

public class ProdutosDto {

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
    @ExistsId(field = "id", table = Categoria.class)
    @ManyToOne
    private Categoria categoria;

    @Size(min = 3)
    private List<Caracteristica> caracteristica;
    @NotNull
    @Min(value = 0)
    private Integer quantidadeDisponivel;

    @ManyToOne
    private Usuario dono;

    public ProdutosDto(@NotBlank String nome, @Positive int quantidade,
                       @NotBlank @Length(max=1000) String descricao,
                       @NotNull @Positive BigDecimal valor, @NotNull Categoria categoria, Usuario dono,
    List<Caracteristica> caracteristica, Integer quantidadeDisponivel) {
        this.nome = nome;
        this.quantidade = quantidade;
        this.descricao = descricao;
        this.valor = valor;
        this.categoria = categoria;
        this.dono = dono;
        this.caracteristica = caracteristica;
        this.quantidadeDisponivel = quantidadeDisponivel;
    }

    @Deprecated
    public ProdutosDto(){}

    public Produtos toModel(EntityManager manager, Usuario dono){
       Categoria categoria = manager.find(Categoria.class, getCategoria());
       return new Produtos(nome,quantidade,descricao,valor,categoria,dono);
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

    @Override
    public String toString() {
        return "ProdutosDto{" +
                "nome='" + nome + '\'' +
                ", quantidade=" + quantidade +
                ", descricao='" + descricao + '\'' +
                ", valor=" + valor +
                ", categoria=" + categoria +
                ", dono=" + dono +
                '}';
    }
}

