package br.com.zupacademy.adriano.mercadolivre.entidades;

import org.hibernate.validator.constraints.URL;
import org.springframework.jmx.export.annotation.ManagedAttribute;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class ImagemProduto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @NotNull
    @Valid
    private Produtos produtos;
    @URL
    @NotBlank
    private String link;

    public ImagemProduto(@NotNull @Valid Produtos produtos, @URL @NotBlank String link) {
        this.produtos = produtos;
        this.link = link;
    }

    @Deprecated
    public ImagemProduto(){}

    @Override
    public String toString() {
        return "ImagemProduto{" +
                "id=" + id +
                ", produtos=" + produtos +
                ", link='" + link + '\'' +
                '}';
    }
}
