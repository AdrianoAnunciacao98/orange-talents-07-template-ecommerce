package br.com.zupacademy.adriano.mercadolivre.view;

import br.com.zupacademy.adriano.mercadolivre.controllers.dto.CaracteristicaDto;
import br.com.zupacademy.adriano.mercadolivre.controllers.dto.OpiniaoDto;
import br.com.zupacademy.adriano.mercadolivre.controllers.dto.PerguntaDto;
import br.com.zupacademy.adriano.mercadolivre.entidades.Caracteristica;
import br.com.zupacademy.adriano.mercadolivre.entidades.Opiniao;
import br.com.zupacademy.adriano.mercadolivre.entidades.Produtos;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class DetalheProdutoView {

    private List<String> imagens;
    private String nomeProduto;
    private BigDecimal precoProduto;
    private List<CaracteristicaDto> caracteristicas;
    private String descricaoProduto;
    private List<OpiniaoDto> opinioes;
    private List<PerguntaDto> perguntas;
    private Double mediaNotas;
    private Double totalNotas;

    public DetalheProdutoView(Produtos produto) {
        this.imagens = produto.getImagens().stream().map(item -> item.getLink()).collect(Collectors.toList());
        this.nomeProduto = produto.getNome();
        this.precoProduto = produto.getValor();
        this.caracteristicas = CaracteristicaDto.doProduto(produto.getCaracteristica());
        this.descricaoProduto = produto.getDescricao();
        this.opinioes = OpiniaoDto.doProduto(produto);
        this.perguntas = PerguntaDto.doProduto(produto);
        this.mediaNotas = calcularMediaNotas(produto);
        this.totalNotas = calcularTotalNotas(produto);
    }

    private Double calcularTotalNotas(Produtos produto) {
        Integer resultado = 0;
        for (Opiniao opiniao : produto.getOpinioes()) {
            resultado+=opiniao.getNota();
        }
        return Double.valueOf(resultado);
    }

    private Double calcularMediaNotas(Produtos produto) {
        Double resultado = 0.0;
        for (Opiniao opiniao : produto.getOpinioes()) {
            resultado+=opiniao.getNota();
        }
        return resultado/produto.getOpinioes().size();
    }

    public List<String> getImagens() {
        return imagens;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public BigDecimal getPrecoProduto() {
        return precoProduto;
    }

    public List<CaracteristicaDto> getCaracteristicas() {
        return caracteristicas;
    }

    public String getDescricaoProduto() {
        return descricaoProduto;
    }

    public List<OpiniaoDto> getOpinioes() {
        return opinioes;
    }

    public List<PerguntaDto> getPerguntas() {
        return perguntas;
    }

    public Double getMediaNotas() {
        return mediaNotas;
    }

    public Double getTotalNotas() {
        return totalNotas;
    }
}
