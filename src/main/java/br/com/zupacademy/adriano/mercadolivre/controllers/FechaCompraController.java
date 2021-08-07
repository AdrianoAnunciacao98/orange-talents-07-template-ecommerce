package br.com.zupacademy.adriano.mercadolivre.controllers;

import br.com.zupacademy.adriano.mercadolivre.controllers.dto.CompraDto;
import br.com.zupacademy.adriano.mercadolivre.entidades.Compra;
import br.com.zupacademy.adriano.mercadolivre.entidades.Produtos;
import br.com.zupacademy.adriano.mercadolivre.entidades.Usuario;
import br.com.zupacademy.adriano.mercadolivre.repository.UsuarioRepository;
import br.com.zupacademy.adriano.mercadolivre.seguranca.UsuarioLogado;
import br.com.zupacademy.adriano.mercadolivre.utils.GatewayPagamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.Optional;

@RestController
public class FechaCompraController {

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping (value = "/compras")
    @Transactional
    public ResponseEntity<?> comprar(@RequestBody @Valid CompraDto compraDto,
                                     @AuthenticationPrincipal UsuarioLogado usuarioLogado, UriComponentsBuilder builder)
            throws BindException {
       Produtos produtocompra =  manager.find(Produtos.class, compraDto.getProduto());

       boolean abataEstoque = produtocompra.abataEstoque(compraDto.getQuantidade());

       int quantidade = compraDto.getQuantidade();
       //se tirou do estoque, pega o usuário logado, instancia uma nova compra e persiste essa nova compra.
       if(abataEstoque){
           Optional<Usuario> comprador = usuarioRepository.findByLogin(usuarioLogado.getUsername());
           GatewayPagamento gatewayPagamento = compraDto.getGateway();
           Compra novaCompra =  new Compra(produtocompra,quantidade, comprador.get(), compraDto.getGateway());
           manager.persist(novaCompra);

    //      String pagseguro = "paypal.com/" + novaCompra.getId()+ "?redirectUrl={urlRetornoAppPosPagamento}";

           if(gatewayPagamento.equals(GatewayPagamento.PAGSEGURO)){
               String urlPagSeguro = builder.path("/retorno-pagseguro/{id}").buildAndExpand(novaCompra.getId()).toString();
               return ResponseEntity.status(302).body("pagseguro.com?returnId="+novaCompra.getId()+
                       "&redirectUrl="+urlPagSeguro);
           } else {
               String urlPaypal = builder.path("/retorno-paypal/{id}").buildAndExpand(novaCompra.getId()).toString();
               return ResponseEntity.status(302).body("paypal.com?returnId="+novaCompra.getId()+
                       "&redirectUrl="+urlPaypal);
           }

       }

       BindException problemaComEstoque = new BindException(compraDto,
                "compraDto");
        problemaComEstoque.reject(null,
                "Não foi possível realizar a compra por conta do estoque");

        throw problemaComEstoque;



        }

    @GetMapping("/compras/{id}")
    public ResponseEntity<?> lista(@PathVariable Long id){
        return ResponseEntity.ok(manager.find(Compra.class, id));
    }
    }


