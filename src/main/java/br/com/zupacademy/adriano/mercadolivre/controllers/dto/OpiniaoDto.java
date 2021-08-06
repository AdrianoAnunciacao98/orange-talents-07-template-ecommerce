package br.com.zupacademy.adriano.mercadolivre.controllers.dto;

import br.com.zupacademy.adriano.mercadolivre.entidades.Opiniao;
import br.com.zupacademy.adriano.mercadolivre.entidades.Produtos;
import br.com.zupacademy.adriano.mercadolivre.entidades.Usuario;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;
import java.util.stream.Collectors;

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

    public OpiniaoDto(String descricao, String titulo, int nota) {
        this.descricao = descricao;
        this.titulo = titulo;
        this.nota = nota;
    }

    public static List<OpiniaoDto> doProduto(Produtos produto) {
        return produto.getOpinioes()
                .stream()
                .map(item->new OpiniaoDto(item.getDescricao(), item.getTitulo(), item.getNota()))
                .collect(Collectors.toList());
    }

    public Opiniao toModel(@NotNull @Valid Produtos produto, @NotNull @Valid Usuario consumidor) {
        return new Opiniao(nota,titulo,descricao,produto,consumidor);
    }


}
