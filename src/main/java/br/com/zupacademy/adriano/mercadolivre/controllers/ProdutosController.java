package br.com.zupacademy.adriano.mercadolivre.controllers;

import br.com.zupacademy.adriano.mercadolivre.controllers.dto.ProdutosDto;
import br.com.zupacademy.adriano.mercadolivre.controllers.dto.UsuarioDto;
import br.com.zupacademy.adriano.mercadolivre.entidades.Categoria;
import br.com.zupacademy.adriano.mercadolivre.entidades.Produtos;
import br.com.zupacademy.adriano.mercadolivre.entidades.Usuario;
import br.com.zupacademy.adriano.mercadolivre.repository.ProdutosRepository;
import br.com.zupacademy.adriano.mercadolivre.repository.UsuarioRepository;
import br.com.zupacademy.adriano.mercadolivre.seguranca.UsuarioLogado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/produtos")
public class ProdutosController {
    @PersistenceContext
    private EntityManager manager;

        @Autowired
        public ProdutosRepository produtosRepository;
        @Autowired
        public UsuarioRepository usuarioRepository;


            //(value = "/produtos")
            @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody @Valid ProdutosDto produtosDto){
                UsuarioDto dono = usuarioRepository.findByLogin("adriano@gmail.com").get();
                //depois qualquer coisa tirar esse dono daqui e do produtosdto também.
                Produtos produto = produtosDto.toModel(manager, dono.toModel());
                manager.persist(produto);
                return new ResponseEntity(produto.toString(), HttpStatus.OK);
    }
}
