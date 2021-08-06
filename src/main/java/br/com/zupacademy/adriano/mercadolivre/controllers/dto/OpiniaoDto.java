package br.com.zupacademy.adriano.mercadolivre.controllers.dto;

import br.com.zupacademy.adriano.mercadolivre.entidades.Opiniao;
import br.com.zupacademy.adriano.mercadolivre.entidades.Produtos;
import br.com.zupacademy.adriano.mercadolivre.entidades.Usuario;

import javax.validation.Valid;
import javax.validation.constraints.*;

public class OpiniaoDto {

    @Min(1)
    @Max(5)
    private int nota;
    @NotBlank
    private String titulo;

    @NotBlank
    @Size(max=500)
    private String descricao;

    @Deprecated
    public OpiniaoDto(){}

    public OpiniaoDto(int nota, String titulo, String descricao) {
        this.nota = nota;
        this.titulo = titulo;
        this.descricao = descricao;
    }

    public Opiniao toModel(@NotNull @Valid Produtos produto, @NotNull @Valid Usuario consumidor) {
        return new Opiniao(nota,titulo,descricao,produto,consumidor);
    }
}
