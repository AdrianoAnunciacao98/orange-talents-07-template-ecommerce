package br.com.zupacademy.adriano.mercadolivre.exceptions;

public class ErrorsConfig {
    private String field;
    private String message;

    public ErrorsConfig(String field, String message) {
        this.field = field;
        this.message = message;
    }

    public String getField() {
        return field;
    }

    public String getMessage() {
        return message;
    }
}
