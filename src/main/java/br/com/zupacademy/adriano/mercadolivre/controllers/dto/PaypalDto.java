package br.com.zupacademy.adriano.mercadolivre.controllers.dto;

import br.com.zupacademy.adriano.mercadolivre.entidades.Compra;
import br.com.zupacademy.adriano.mercadolivre.entidades.Transacao;
import br.com.zupacademy.adriano.mercadolivre.utils.RetornoGatewayPagamento;
import br.com.zupacademy.adriano.mercadolivre.utils.StatusTransacao;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class PaypalDto implements RetornoGatewayPagamento {

    @Min(0)
    @Max(1)
    private int status;

    @NotBlank
    private String idTransacao;

    public PaypalDto(@Min(0) @Max(1) int status, @NotBlank String idTransacao) {
        this.status = status;
        this.idTransacao = idTransacao;
    }

    public Transacao toTransacao(Compra compra) {

        StatusTransacao statusCalculado = this.status==0? StatusTransacao.erro:StatusTransacao.sucesso;
        return new Transacao(statusCalculado,
                idTransacao,compra);
    }
}
