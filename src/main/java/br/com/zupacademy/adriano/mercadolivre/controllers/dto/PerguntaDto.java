package br.com.zupacademy.adriano.mercadolivre.controllers.dto;


import br.com.zupacademy.adriano.mercadolivre.entidades.Pergunta;
import br.com.zupacademy.adriano.mercadolivre.entidades.Produtos;
import br.com.zupacademy.adriano.mercadolivre.entidades.Usuario;
import br.com.zupacademy.adriano.mercadolivre.repository.UsuarioRepository;

import javax.persistence.EntityManager;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

public class PerguntaDto {

    @NotBlank
    private String titulo;

    @NotNull
    @Valid
    @ManyToOne
    private Usuario cliente;

    @NotNull
    @Valid
    @ManyToOne
    private Produtos produto;


    public PerguntaDto(String titulo) {
        this.titulo = titulo;
    }

    public static List<PerguntaDto> doProduto(Produtos produto) {
        return produto.getPerguntas().stream().map(item->new PerguntaDto(item.getTitulo())).collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return "PerguntaDto{" +
                "titulo='" + titulo + '\'' +
                '}';
    }

    public Pergunta toModel(EntityManager manager, Usuario usuario){
        return new Pergunta(this.titulo, this.cliente, manager.find(Produtos.class, this.produto));
    }

    @Deprecated
    public PerguntaDto(){}

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}
