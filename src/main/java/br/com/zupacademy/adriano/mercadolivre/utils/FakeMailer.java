package br.com.zupacademy.adriano.mercadolivre.utils;

import org.springframework.stereotype.Component;

@Component
public class FakeMailer  implements Mailer{


    @Override
    public void send(String body, String subject, String nomeEnvio, String from, String destino) {
        System.out.println(body);
        System.out.println(subject);
        System.out.println(nomeEnvio);
        System.out.println(from);
        System.out.println(destino);

    }
}
