package br.com.zupacademy.adriano.mercadolivre.controllers.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class NotaFiscalDto {

    @NotNull
    private Long idCompra;
    @NotNull
    private Long idComprador;

    public NotaFiscalDto(Long idCompra, Long idComprador) {
        this.idCompra = idCompra;
        this.idComprador = idComprador;
    }

    @Override
    public String toString() {
        return "NotaFiscalDto{" +
                "idCompra=" + idCompra +
                ", idComprador=" + idComprador +
                '}';
    }
}
