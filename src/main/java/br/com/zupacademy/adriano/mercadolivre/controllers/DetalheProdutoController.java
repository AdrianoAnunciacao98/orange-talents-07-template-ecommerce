package br.com.zupacademy.adriano.mercadolivre.controllers;

import br.com.zupacademy.adriano.mercadolivre.entidades.Produtos;
import br.com.zupacademy.adriano.mercadolivre.view.DetalheProdutoView;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@RestController
public class DetalheProdutoController {

    @PersistenceContext
    private EntityManager manager;

    @GetMapping (value = "/produtos/{id}")
    public ResponseEntity<?> detalhar(@PathVariable("id") Long id){
        Produtos produto = manager.find(Produtos.class, id);
        if(produto == null)
            return ResponseEntity.badRequest().build();

        else {
            return ResponseEntity.ok(new DetalheProdutoView(produto));
        }

    }
}
