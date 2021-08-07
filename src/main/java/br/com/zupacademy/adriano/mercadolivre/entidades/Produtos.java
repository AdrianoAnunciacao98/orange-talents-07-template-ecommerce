package br.com.zupacademy.adriano.mercadolivre.entidades;

import br.com.zupacademy.adriano.mercadolivre.controllers.dto.PerguntaDto;
import br.com.zupacademy.adriano.mercadolivre.controllers.validator.ExistsId;
import com.fasterxml.classmate.Annotations;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

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
    private Long categoria;
    @NotNull
    @Min(value = 0)
    private Integer quantidadeDisponivel;

    @Size(min = 3)
    @Type(type = "json")
    @Column(columnDefinition = "json")
    private List<Caracteristica> caracteristicas;

    private Instant instanteCadastro;

    @ManyToOne
    @NotNull
    @Valid
    private Usuario dono;

    @OneToMany(mappedBy = "produtos", cascade = CascadeType.MERGE)
    private Set<ImagemProduto> imagens = new HashSet<>();

    @OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE)
    private List<Pergunta> perguntas;

    @OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE)
    private List<Opiniao> opinioes;


    public Produtos(String nome, int quantidade, String descricao, BigDecimal valor, Long categoria, List<Caracteristica> caracteristicas, Usuario dono) {
        this.nome = nome;
        this.quantidade = quantidade;
        this.descricao = descricao;
        this.valor = valor;
        this.categoria = categoria;
        this.caracteristicas = caracteristicas;
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

    public Long getCategoria() {
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

    public void associaImagens(Set<String> links) {
      Set<ImagemProduto> imagens =   links.stream().map(link -> new ImagemProduto(this,link)).collect(Collectors.toSet());
      //associar as imagens com as imagens do produto:
        this.imagens.addAll(imagens);


    }

    public Set<ImagemProduto> getImagens() {
        return imagens;
    }

    public boolean pertenceAoUsuario(Optional<Usuario> dono) {
        return this.dono.equals(dono);
    }



    public List<Pergunta> getPerguntas() {
        return perguntas;
    }


    public List<Caracteristica> getCaracteristica() {
        return caracteristicas;
    }

    public List<Opiniao> getOpinioes() {
        return opinioes;
    }

    public boolean abataEstoque(@Positive int quantidade) {
        if(quantidade<=this.quantidadeDisponivel){
            this.quantidadeDisponivel-=quantidade;
            return true;
        }

        return false;

    }
}
