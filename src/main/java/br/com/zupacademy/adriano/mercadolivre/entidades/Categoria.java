package br.com.zupacademy.adriano.mercadolivre.entidades;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Entity
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    @Positive
    private Long idCategoriamae;

    @ManyToOne
    private Categoria categoriaMae;

    public Categoria(String nome, Long idCategoriamae) {
        this.nome = nome;
        this.idCategoriamae = idCategoriamae;
    }
    @Deprecated
    public Categoria(){}

    public Categoria(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Categoria{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", idCategoriamae=" + idCategoriamae +
                ", categoriaMae=" + categoriaMae +
                '}';
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setIdCategoriamae(Long idCategoriamae) {
        this.idCategoriamae = idCategoriamae;
    }

    public void setMae(Categoria categoriaMae) {
        this.categoriaMae = categoriaMae;
    }
}
