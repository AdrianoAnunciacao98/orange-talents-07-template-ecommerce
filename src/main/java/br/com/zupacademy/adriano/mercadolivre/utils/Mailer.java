package br.com.zupacademy.adriano.mercadolivre.utils;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public interface Mailer {
    /**
     *
     * @param body - corpo
     * @param subject - assunto
     * @param nomeEnvio - nome para aparecer
     * @param from - email de origem
     * @param destino - email de destino
     */
    void send(@NotBlank String body, @NotBlank String subject, @NotBlank String nomeEnvio,
              @NotBlank @Email String from, @NotBlank @Email String destino);
}
