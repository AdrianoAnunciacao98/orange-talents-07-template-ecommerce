package br.com.zupacademy.adriano.mercadolivre.repository;

import br.com.zupacademy.adriano.mercadolivre.entidades.Produtos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutosRepository  extends JpaRepository<Produtos,Long> {
}
