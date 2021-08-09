package br.com.zupacademy.adriano.mercadolivre.utils;

import br.com.zupacademy.adriano.mercadolivre.entidades.Compra;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

public class EventosNovaCompra {

    @Autowired
    //1
    private Set<EventoCompraSucesso> eventosCompraSucesso;

    public void processa(Compra compra) {
        //1
        if(compra.processadaComSucesso()) {
            //1
            eventosCompraSucesso.forEach(evento -> evento.processa(compra));
        }
        else {
            //eventosFalha
        }
    }
}
