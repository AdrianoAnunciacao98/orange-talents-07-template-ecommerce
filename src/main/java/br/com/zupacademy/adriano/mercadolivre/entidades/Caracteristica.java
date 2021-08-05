package br.com.zupacademy.adriano.mercadolivre.entidades;

import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class Caracteristica {
    @NotBlank
    private String nome;

    @NotBlank
    private String descricao;

    @Deprecated
    public Caracteristica() {}

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Caracteristica that = (Caracteristica) o;
        return Objects.equals(nome, that.nome) && Objects.equals(descricao, that.descricao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, descricao);
    }

    @Override
    public String toString() {
        return "Caracteristica{" +
                "nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                '}';
    }

    public boolean verificaCaracteristica(List<Caracteristica> caracteristicas) {
        Set<String> iguais = new HashSet<>();
        int contador = 0;
        for (Caracteristica caracteristica1 : caracteristicas) {
            if(!iguais.add(caracteristica1.getNome()))return false;
        }
        return true;
    }
}
