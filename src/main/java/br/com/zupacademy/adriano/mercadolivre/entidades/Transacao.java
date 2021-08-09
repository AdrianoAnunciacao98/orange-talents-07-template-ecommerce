package br.com.zupacademy.adriano.mercadolivre.entidades;

import br.com.zupacademy.adriano.mercadolivre.utils.StatusTransacao;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Transacao {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private  LocalDateTime instante;
    @NotNull
    private StatusTransacao status;
    @NotBlank
    private String idTransacaoGateway;

    @NotNull @Valid @ManyToOne
    private Compra compra;

    public Transacao(@NotNull StatusTransacao status, @NotBlank String idTransacaoGateway,
                     @NotNull @Valid Compra compra) {
        this.status = status;
        this.idTransacaoGateway = idTransacaoGateway;
        this.instante = LocalDateTime.now();
        this.compra = compra;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transacao transacao = (Transacao) o;
        return Objects.equals(id, transacao.id);
    }

    public boolean concluidaComSucesso(){
        return this.status.equals(StatusTransacao.sucesso);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Deprecated
    public Transacao(){}
}
