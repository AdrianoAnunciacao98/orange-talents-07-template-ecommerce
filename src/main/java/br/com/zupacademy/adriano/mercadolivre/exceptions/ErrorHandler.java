package br.com.zupacademy.adriano.mercadolivre.exceptions;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ErrorHandler {
    private MessageSource messageSource;

    public ErrorHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrorsConfig>> handle(MethodArgumentNotValidException exception){
        List<ErrorsConfig> listaErro = new ArrayList<>();
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();

        fieldErrors.forEach(e -> {
            String mensagem = messageSource.getMessage(e, LocaleContextHolder.getLocale());
            ErrorsConfig erro = new ErrorsConfig(e.getField(), mensagem);
            listaErro.add(erro);
        });

        return ResponseEntity.badRequest().body(listaErro);
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<List<ErrorsConfig>> handle(BindException exception){
        List<ErrorsConfig> listaErro = new ArrayList<>();
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();

        fieldErrors.forEach(e -> {
            String mensagem = messageSource.getMessage(e, LocaleContextHolder.getLocale());
            ErrorsConfig erro = new ErrorsConfig(e.getField(), mensagem);
            listaErro.add(erro);
        });

        return ResponseEntity.badRequest().body(listaErro);
    }

    @ExceptionHandler(CaracteristicasInvalidasException.class)
    public ResponseEntity<ErrorsConfig> handle(CaracteristicasInvalidasException exception){
        return ResponseEntity.badRequest().body(new ErrorsConfig("Caracterisica", exception.getMessage()));
    }
}
