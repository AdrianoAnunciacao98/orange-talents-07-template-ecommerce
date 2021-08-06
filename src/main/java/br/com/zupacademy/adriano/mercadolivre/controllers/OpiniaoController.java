package br.com.zupacademy.adriano.mercadolivre.controllers;

import br.com.zupacademy.adriano.mercadolivre.controllers.dto.OpiniaoDto;
import br.com.zupacademy.adriano.mercadolivre.entidades.Opiniao;
import br.com.zupacademy.adriano.mercadolivre.entidades.Produtos;
import br.com.zupacademy.adriano.mercadolivre.entidades.Usuario;
import br.com.zupacademy.adriano.mercadolivre.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Optional;

@RestController
public class OpiniaoController {

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping(value = "/produtos/{id}/opiniao")
    @Transactional
    public ResponseEntity<?> adiciona(@RequestBody @Valid OpiniaoDto opiniaoDto, @PathVariable("id") Long id){
        Produtos produto = manager.find(Produtos.class, id);
       Usuario consumidor = usuarioRepository.findByLogin("adriano@gmail.com").get();
        //opiniao é dada a um produto, assim como é feita por um consumidor.
      Opiniao novaOpiniao =   opiniaoDto.toModel(produto,consumidor);
      manager.persist(novaOpiniao);

       return new ResponseEntity(novaOpiniao.toString(), HttpStatus.OK);
    }
}
