package br.com.zupacademy.adriano.mercadolivre.controllers.dto;

import br.com.zupacademy.adriano.mercadolivre.entidades.Caracteristica;

import java.util.List;
import java.util.stream.Collectors;

public class CaracteristicaDto {

    private String nome;
    private String descricao;

    public CaracteristicaDto(Caracteristica caracteristica) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public static List<CaracteristicaDto> doProduto(List<Caracteristica> caracteristica) {
        return caracteristica.stream().map(CaracteristicaDto::new).collect(Collectors.toList());
    }
}
