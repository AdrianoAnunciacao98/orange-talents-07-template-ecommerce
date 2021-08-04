package br.com.zupacademy.adriano.mercadolivre.controllers.dto;

import br.com.zupacademy.adriano.mercadolivre.controllers.validator.ExistsId;
import br.com.zupacademy.adriano.mercadolivre.controllers.validator.UniqueValue;
import br.com.zupacademy.adriano.mercadolivre.entidades.Categoria;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

public class CategoriaDto {

    @NotBlank
    @UniqueValue(field = "nome", table = Categoria.class)
    private String nome;

    @Positive
    @ExistsId(field = "id", table = Categoria.class, message = "Essa categoria não existe no sistema")
    private Long idCategoriamae;


    @Override
    public String toString() {
        return "CategoriaDto{" +
                "nome='" + nome + '\'' +
                ", idCategoriamae=" + idCategoriamae +
                '}';
    }
    @Deprecated
    public CategoriaDto(){}


    public CategoriaDto(String nome, Long idCategoriamae) {
        this.nome = nome;
        this.idCategoriamae = idCategoriamae;
    }

    public Categoria toModel(EntityManager manager){

        Categoria categoria = new Categoria(nome);
        if(this.idCategoriamae == null){
            return new Categoria(this.nome);
        }
        return new Categoria(this.nome, this.idCategoriamae);


       // return new Categoria(this.nome = nome, this.idCategoriamae = idCategoriamae);
    }
}
