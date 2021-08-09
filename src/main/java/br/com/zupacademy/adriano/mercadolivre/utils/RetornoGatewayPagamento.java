package br.com.zupacademy.adriano.mercadolivre.utils;

import br.com.zupacademy.adriano.mercadolivre.entidades.Compra;
import br.com.zupacademy.adriano.mercadolivre.entidades.Transacao;



public interface RetornoGatewayPagamento {
    Transacao toTransacao(Compra compra);
}
