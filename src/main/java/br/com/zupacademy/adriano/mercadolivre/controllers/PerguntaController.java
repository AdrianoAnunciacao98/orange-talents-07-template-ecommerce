package br.com.zupacademy.adriano.mercadolivre.controllers;

import br.com.zupacademy.adriano.mercadolivre.controllers.dto.PerguntaDto;
import br.com.zupacademy.adriano.mercadolivre.controllers.dto.UsuarioDto;
import br.com.zupacademy.adriano.mercadolivre.entidades.Pergunta;
import br.com.zupacademy.adriano.mercadolivre.entidades.Produtos;
import br.com.zupacademy.adriano.mercadolivre.entidades.Usuario;
import br.com.zupacademy.adriano.mercadolivre.repository.UsuarioRepository;
import br.com.zupacademy.adriano.mercadolivre.seguranca.UsuarioLogado;
import br.com.zupacademy.adriano.mercadolivre.utils.Emails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Optional;

@RestController
public class PerguntaController {

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private Emails emails;

    @PostMapping(value = "/produtos/{id}/perguntas")
    @Transactional
    public ResponseEntity<?> criar(@RequestBody @Valid PerguntaDto perguntaDto, @PathVariable("id") Long id
    ,  @AuthenticationPrincipal UsuarioLogado usuarioLogado){
        Optional<Usuario> optUsuario = usuarioRepository.findByLogin(usuarioLogado.getUsername());
        Pergunta pergunta = perguntaDto.toModel(manager, optUsuario.get());
        manager.persist(pergunta);

        Produtos produto = manager.find(Produtos.class, id);
        //funcionalidade para mandar email com a pergunta:


        emails.pergunta(pergunta);


        //criar um conjunto de classes que espere esse json: chave e mensagem - mensagem(html, texto)
        //

        return new ResponseEntity(pergunta.toString(), HttpStatus.OK);
    }
}
