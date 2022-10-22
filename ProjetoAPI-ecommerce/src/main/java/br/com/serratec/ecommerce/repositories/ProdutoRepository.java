package br.com.serratec.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.serratec.ecommerce.domains.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
	
}
