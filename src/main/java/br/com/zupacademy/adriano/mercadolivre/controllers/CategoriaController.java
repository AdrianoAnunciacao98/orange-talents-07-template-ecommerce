package br.com.zupacademy.adriano.mercadolivre.controllers;

import br.com.zupacademy.adriano.mercadolivre.controllers.dto.CategoriaDto;
import br.com.zupacademy.adriano.mercadolivre.entidades.Categoria;
import br.com.zupacademy.adriano.mercadolivre.entidades.Usuario;
import br.com.zupacademy.adriano.mercadolivre.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/categoria")
public class CategoriaController {

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    public CategoriaRepository categoriaRepository;

    @PostMapping
       //     (value = "/categorias")
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<?> cadastrar(@RequestBody @Valid CategoriaDto categoriaDto){
        Categoria categoria = categoriaDto.toModel(manager);
        categoriaRepository.save(categoria);
        return new ResponseEntity(categoria.toString(), HttpStatus.OK);
    }
}
