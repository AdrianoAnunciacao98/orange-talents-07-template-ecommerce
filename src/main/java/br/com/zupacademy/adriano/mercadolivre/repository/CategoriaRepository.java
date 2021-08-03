package br.com.zupacademy.adriano.mercadolivre.repository;

import br.com.zupacademy.adriano.mercadolivre.entidades.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}
