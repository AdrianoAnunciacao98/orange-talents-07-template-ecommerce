package br.com.zupacademy.adriano.mercadolivre.entidades;

import br.com.zupacademy.adriano.mercadolivre.controllers.dto.PagSeguroDto;
import br.com.zupacademy.adriano.mercadolivre.utils.GatewayPagamento;
import br.com.zupacademy.adriano.mercadolivre.utils.RetornoGatewayPagamento;
import com.zaxxer.hikari.util.ConcurrentBag;
import io.jsonwebtoken.lang.Assert;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

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
    @OneToMany(mappedBy = "compra", cascade = CascadeType.MERGE)
    private Set<Transacao> transacoes = new HashSet<>();

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
                ", gatewayPagamento=" + gatewayPagamento +
                ", transacoes=" + transacoes +
                '}';
    }

    public Long getId() {
        return id;
    }

    public Usuario getComprador() {
        return comprador;
    }

    public String urlRedirecionamento(
            UriComponentsBuilder uriComponentsBuilder) {
        return this.gatewayPagamento.criaUrlRetorno(this, uriComponentsBuilder);
    }

    public void adicionaTransacao(@Valid RetornoGatewayPagamento retornoGateway) {

            Transacao novaTransacao = retornoGateway.toTransacao(this);
        Assert.isTrue(!this.transacoes.contains(novaTransacao), "Já existe uma transação igual" + novaTransacao);
        Assert.isTrue(transacoesConcluidasComSucesso().isEmpty(), "Essa compra já foi concluida com sucesso");

        this.transacoes.add(retornoGateway.toTransacao(this));
    }
    @Deprecated
    public Compra(){}

    public Set<Transacao> transacoesConcluidasComSucesso() {
        Set<Transacao> transacoesConcluidasComSucesso = this.transacoes.stream().filter(Transacao :: concluidaComSucesso).collect(Collectors.toSet());
        Assert.isTrue(transacoesConcluidasComSucesso.isEmpty(), "Essa compra já foi concluida com sucesso");

        Assert.isTrue(transacoesConcluidasComSucesso.size() <=1, "Deu ruim, tem mais de 1 transações concluida com sucesso"+ this.id);

        return transacoesConcluidasComSucesso;
    }

    public boolean processadaComSucesso() {
        return !transacoesConcluidasComSucesso().isEmpty();
    }
}
