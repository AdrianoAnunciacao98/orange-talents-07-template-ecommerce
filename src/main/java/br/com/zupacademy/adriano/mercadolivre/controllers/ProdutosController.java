package br.com.zupacademy.adriano.mercadolivre.controllers;

import br.com.zupacademy.adriano.mercadolivre.controllers.dto.ImagensDto;
import br.com.zupacademy.adriano.mercadolivre.controllers.dto.ProdutosDto;
import br.com.zupacademy.adriano.mercadolivre.controllers.dto.UsuarioDto;
import br.com.zupacademy.adriano.mercadolivre.controllers.validator.ProibeCaracteristicaComNomeIgualValidator;
import br.com.zupacademy.adriano.mercadolivre.controllers.validator.ProibeEmailDuplicadoValidator;
import br.com.zupacademy.adriano.mercadolivre.entidades.Categoria;
import br.com.zupacademy.adriano.mercadolivre.entidades.Produtos;
import br.com.zupacademy.adriano.mercadolivre.entidades.Usuario;
import br.com.zupacademy.adriano.mercadolivre.repository.ProdutosRepository;
import br.com.zupacademy.adriano.mercadolivre.repository.UsuarioRepository;
import br.com.zupacademy.adriano.mercadolivre.seguranca.UsuarioLogado;
import br.com.zupacademy.adriano.mercadolivre.utils.UploaderFake;
import br.com.zupacademy.adriano.mercadolivre.utils.uploaderFake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/produtos")
public class ProdutosController {
    @PersistenceContext
    private EntityManager manager;

        @Autowired
        private ProdutosRepository produtosRepository;
        @Autowired
        private UsuarioRepository usuarioRepository;

        @Autowired
        private UploaderFake uploaderFake;

        @InitBinder(value = "ProdutosDto")
        public void init(WebDataBinder webDataBinder){
            webDataBinder.addValidators(new ProibeCaracteristicaComNomeIgualValidator());
        }


            //(value = "/produtos")
            @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody @Valid ProdutosDto produtosDto,
            @AuthenticationPrincipal UsuarioLogado dono){

                //depois qualquer coisa tirar esse dono daqui e do produtosdto também.
                Produtos produto = produtosDto.toModel(manager, dono, usuarioRepository);
                manager.persist(produto);
                return new ResponseEntity(produto.toString(), HttpStatus.OK);
    }

    //enviar imagens para o local de destino
    // pegar o link das imagens e associar com o produto.
    //carregar o produto
    //depois de associar, atualizar o produto com a nova imagem.


    @PostMapping("/{id}/imagens")
    @Transactional
    public ResponseEntity<?> salvaImagens(@PathVariable("id") Long id,
                                          @Valid ImagensDto request,
                                          @AuthenticationPrincipal UsuarioLogado usuarioLogado){
        Produtos produto = manager.find(Produtos.class, id);
        Optional<Usuario> dono = usuarioRepository.findByLogin(usuarioLogado.getUsername());
        if(!produto.pertenceAoUsuario(dono)){
            throw  new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
        Set<String> links = uploaderFake.envia(request.getImagens());
        produto.associaImagens(links);

        manager.merge(produto);
        return ResponseEntity.ok().build();
} }
