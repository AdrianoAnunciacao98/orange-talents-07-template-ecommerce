package br.com.zupacademy.adriano.mercadolivre.controllers;

import br.com.zupacademy.adriano.mercadolivre.controllers.dto.NotaFiscalDto;
import br.com.zupacademy.adriano.mercadolivre.controllers.dto.RankingDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class OutrosSistemasController {

    @PostMapping(value = "/notas-fiscais")
    public void criaNota(@Valid @RequestBody NotaFiscalDto notaFiscalDto){
        System.out.println("nota : " + notaFiscalDto);
    }

    @PostMapping(value = "/ranking")
    public void ranking(@Valid @RequestBody RankingDto rankingDto){
        System.out.println("Ranking : " + rankingDto);
    }
}
