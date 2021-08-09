package br.com.zupacademy.adriano.mercadolivre.controllers.dto;


import javax.validation.constraints.NotNull;

public class RankingDto {

    @NotNull
    private Long idCompra;

    @NotNull
    private Long idDonoproduto;

    public RankingDto(Long idCompra, Long idDonoproduto) {
        this.idCompra = idCompra;
        this.idDonoproduto = idDonoproduto;
    }

    @Override
    public String toString() {
        return "RankingDto{" +
                "idCompra=" + idCompra +
                ", idDonoproduto=" + idDonoproduto +
                '}';
    }


}
