package br.com.zupacademy.adriano.mercadolivre.controllers.dto;

import br.com.zupacademy.adriano.mercadolivre.controllers.validator.ExistsId;
import br.com.zupacademy.adriano.mercadolivre.entidades.Caracteristica;
import br.com.zupacademy.adriano.mercadolivre.entidades.Categoria;
import br.com.zupacademy.adriano.mercadolivre.entidades.Produtos;
import br.com.zupacademy.adriano.mercadolivre.entidades.Usuario;
import br.com.zupacademy.adriano.mercadolivre.exceptions.CaracteristicasInvalidasException;
import br.com.zupacademy.adriano.mercadolivre.repository.UsuarioRepository;
import br.com.zupacademy.adriano.mercadolivre.seguranca.UsuarioLogado;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;

import javax.persistence.EntityManager;
import javax.persistence.ManyToOne;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    private Long categoria;

    @Size(min = 3)
    private List<Caracteristica> caracteristica;
    @NotNull
    @Min(value = 0)
    private Integer quantidadeDisponivel;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @ManyToOne
    private Usuario dono;

    public ProdutosDto(String nome, int quantidade, String descricao, BigDecimal valor, Long categoria, List<Caracteristica> caracteristica, Integer quantidadeDisponivel, UsuarioRepository usuarioRepository, Usuario dono) {
        this.nome = nome;
        this.quantidade = quantidade;
        this.descricao = descricao;
        this.valor = valor;
        this.categoria = categoria;
        this.caracteristica = caracteristica;
        this.quantidadeDisponivel = quantidadeDisponivel;
        this.usuarioRepository = usuarioRepository;
        this.dono = dono;
    }

    @Deprecated
    public ProdutosDto(){}

    public Produtos toModel(EntityManager manager, UsuarioLogado dono, UsuarioRepository usuarioRepository){
       // Categoria catt = manager.find(Categoria.class, idcategoria);

        return new Produtos(this.nome = nome, this.quantidade = quantidade,
    this.descricao = descricao, this.valor = valor, this.categoria = categoria,
                this.caracteristica = caracteristica, getDono()); }

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



    public Usuario getDono() {
        return dono;
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

    public Set<String> buscaCaracteristicasIguais() {
        HashSet<String> nomesIguais = new HashSet<>();
        HashSet<String> resultados = new HashSet<>();
        // 1
        for (Caracteristica caracteristica : caracteristica) {
            String nome = caracteristica.getNome();
            // 1
            if (!nomesIguais.add(nome)) {
                resultados.add(nome);
            }
        }
        return resultados;
    }
}

