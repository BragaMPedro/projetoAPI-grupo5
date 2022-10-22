package br.com.serratec.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.serratec.ecommerce.domains.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Long>{
    
}
