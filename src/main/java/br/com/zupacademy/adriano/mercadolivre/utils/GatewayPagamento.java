package br.com.zupacademy.adriano.mercadolivre.utils;

import br.com.zupacademy.adriano.mercadolivre.entidades.Compra;
import org.springframework.web.util.UriComponentsBuilder;

public enum GatewayPagamento {

    PAGSEGURO {
        @Override
        public String criaUrlRetorno(Compra compra,
                                     UriComponentsBuilder uriComponentsBuilder) {
            String urlRetornoPagseguro = uriComponentsBuilder
                    .path("/retorno-pagseguro/{id}")
                    .buildAndExpand(compra.getId()).toString();

            return "pagseguro.com/" + compra.getId() + "?redirectUrl="
                    + urlRetornoPagseguro;
        }
    },
    PAYPAL {
        @Override
        public String criaUrlRetorno(Compra compra,
                                     UriComponentsBuilder uriComponentsBuilder) {
            String urlRetornoPaypal = uriComponentsBuilder
                    .path("/retorno-paypal/{id}").buildAndExpand(compra.getId())
                    .toString();

            return "paypal.com/" + compra.getId() + "?redirectUrl=" + urlRetornoPaypal;
        }
    };

     public abstract String criaUrlRetorno(Compra compra,
                                           UriComponentsBuilder uriComponentsBuilder);
}
