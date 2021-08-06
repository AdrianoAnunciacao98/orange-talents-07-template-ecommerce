package br.com.zupacademy.adriano.mercadolivre.utils;

import br.com.zupacademy.adriano.mercadolivre.entidades.Pergunta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Service
public class Emails {

    @Autowired
    private Mailer mailer;

    public void pergunta(@NotNull @Valid Pergunta pergunta) {
    //    new RestTemplate().postForEntity("https://api.mandril.com", mandrilMessage, String.class);
        System.out.println("Uma nova pergunta");

        //corpo do email, titulo do email, nome da pessoa que vai receber o email, nome da pessoa interessada
        //email de destino
        mailer.send("<html>.. </html>", "Nova pergunta...", pergunta.getInteressada().getEmail(),"pergunta@mercadolivre.com", pergunta.getDonoProduto().getEmail());
    }
}
