package br.com.zupacademy.adriano.mercadolivre.controllers;

import br.com.zupacademy.adriano.mercadolivre.controllers.dto.PagSeguroDto;
import br.com.zupacademy.adriano.mercadolivre.controllers.dto.PaypalDto;
import br.com.zupacademy.adriano.mercadolivre.entidades.Compra;
import br.com.zupacademy.adriano.mercadolivre.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Set;

@RestController
public class RedirecionaCompraController {

    @PersistenceContext
    private EntityManager manager;

    private EventosNovaCompra eventosNovaCompra;



    @PostMapping(value = "/retorno-paypal/{id}")
    @Transactional
    public String processamentoPaypal(@PathVariable("id") Long idcompra, @Valid @RequestBody
          PaypalDto paypalDto){

      return processa(idcompra, paypalDto);
    }
    @PostMapping(value = "/retorno-pagseguro/{id}")
    @Transactional
    public String processamentoPagSeguro(@PathVariable("id") Long idcompra, @Valid @RequestBody
            PagSeguroDto pagSeguroDto){

        return processa(idcompra, pagSeguroDto);
    }
    public String processa(Long idcompra, RetornoGatewayPagamento retornoGatewayPagamento){

        Compra compra = manager.find(Compra.class, idcompra);

        compra.adicionaTransacao(retornoGatewayPagamento);

        manager.merge(compra);

      eventosNovaCompra.processa(compra);
        return compra.toString();
    }
}
