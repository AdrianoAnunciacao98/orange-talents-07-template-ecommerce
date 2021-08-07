package br.com.zupacademy.adriano.mercadolivre.controllers.dto;

import br.com.zupacademy.adriano.mercadolivre.entidades.Produtos;
import br.com.zupacademy.adriano.mercadolivre.utils.GatewayPagamento;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class CompraDto {

    @Positive
    private int quantidade;

    @NotNull
    private Produtos produto;

    @NotNull
    private GatewayPagamento gateway;

    public CompraDto(int quantidade, Produtos produto, GatewayPagamento gateway) {
        this.quantidade = quantidade;
        this.produto = produto;
        this.gateway = gateway;
    }

    @Deprecated
    public CompraDto(){}

    public int getQuantidade() {
        return quantidade;
    }

    public Produtos getProduto() {
        return produto;
    }

    public GatewayPagamento getGateway() {
        return gateway;
    }
}
