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
    @UniqueValue(domainClass=Categoria.class, fieldName = "nome")
    private String nome;

    @Positive
    @ExistsId(domainClass = Categoria.class, fieldName = "id")
    private Long idCategoriamae;

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setIdCategoriamae(Long idCategoriamae) {
        this.idCategoriamae = idCategoriamae;
    }


    @Override
    public String toString() {
        return "CategoriaDto{" +
                "nome='" + nome + '\'' +
                ", idCategoriamae=" + idCategoriamae +
                '}';
    }

    public Categoria toModel(EntityManager manager){

        Categoria categoria = new Categoria(nome);

        if(idCategoriamae!=null){
            Categoria categoriaMae = manager.find(Categoria.class, idCategoriamae);
            Assert.notNull(categoriaMae, "O id da categoria mae precisa ser valido");
            categoria.setMae(categoriaMae);
        }
        return categoria;


       // return new Categoria(this.nome = nome, this.idCategoriamae = idCategoriamae);
    }
}
