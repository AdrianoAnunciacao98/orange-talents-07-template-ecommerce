package br.com.zupacademy.adriano.mercadolivre.controllers.dto;

import br.com.zupacademy.adriano.mercadolivre.entidades.Compra;
import br.com.zupacademy.adriano.mercadolivre.entidades.Transacao;
import br.com.zupacademy.adriano.mercadolivre.utils.RetornoGatewayPagamento;
import br.com.zupacademy.adriano.mercadolivre.utils.StatusRetornoPagseguro;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class PagSeguroDto implements RetornoGatewayPagamento {

    @NotBlank
    private String idTransacao;
    @NotNull
    private StatusRetornoPagseguro status;

    public PagSeguroDto(@NotBlank String idTransacao, StatusRetornoPagseguro status) {
        this.idTransacao = idTransacao;
        this.status = status;
    }

    public Transacao toTransacao(Compra compra) {
        return new Transacao(status.normaliza(),idTransacao,compra);
    }
}
