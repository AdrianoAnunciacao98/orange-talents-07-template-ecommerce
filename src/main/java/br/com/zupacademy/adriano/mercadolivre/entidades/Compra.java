package br.com.zupacademy.adriano.mercadolivre.entidades;

import br.com.zupacademy.adriano.mercadolivre.utils.GatewayPagamento;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Optional;

@Entity
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @NotNull
    @Valid
    private Produtos produtocompra;
    @Positive
    private int quantidade;
    @ManyToOne
    @NotNull
    @Valid
    private Usuario comprador;

    @Enumerated
    @NotNull
    private GatewayPagamento gatewayPagamento;

    public Compra(@NotNull @Valid Produtos produtocompra, @Positive int quantidade,
                  @NotNull @Valid Usuario comprador,
                  GatewayPagamento gatewayPagamento) {
        this.produtocompra = produtocompra;
        this.quantidade = quantidade;
        this.comprador = comprador;
        this.gatewayPagamento = gatewayPagamento;
    }

    @Override
    public String toString() {
        return "Compra{" +
                "id=" + id +
                ", produtocompra=" + produtocompra +
                ", quantidade=" + quantidade +
                ", comprador=" + comprador +
                '}';
    }

    public Long getId() {
        return id;
    }
}
