package br.com.zupacademy.adriano.mercadolivre.utils;

import br.com.zupacademy.adriano.mercadolivre.entidades.Compra;

public interface EventoCompraSucesso {
    void processa(Compra compra);
}
