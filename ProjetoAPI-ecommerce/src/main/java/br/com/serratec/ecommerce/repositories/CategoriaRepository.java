package br.com.serratec.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import br.com.serratec.ecommerce.domains.Categoria;

@Repository 
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
	Categoria findByNome_categoria(String nome_categoria);
}