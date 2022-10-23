package br.com.serratec.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.serratec.ecommerce.domains.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

}
