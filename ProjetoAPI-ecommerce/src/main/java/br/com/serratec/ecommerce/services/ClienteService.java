package br.com.serratec.ecommerce.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.serratec.ecommerce.domains.Cliente;
import br.com.serratec.ecommerce.repositories.ClienteRepository;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repositorio;
    
    public List<Cliente> obterTodos() {
        return repositorio.findAll();
    }
    
    public Optional<Cliente> obterPorId(Long id_cliente){
        
        Optional<Cliente> optCliente = repositorio.findById(id_cliente);
        
        if(optCliente.isEmpty()) {
            throw new ResourceNotFoundException("Não foi possivel encontrar o Cliente " + id_cliente);
        }
        
        return optCliente;
    }
    
    public Cliente cadastrar(Cliente cliente) {
                 
        cliente.setCpf(null);
        return repositorio.save(cliente);
    }
    
    public Cliente atualizar(Long id_cliente, Cliente cliente) {
        
        obterPorId(id_cliente);
        
        cliente.setId_cliente(id_cliente);
        return repositorio.save(cliente);
    }
    
    public void deletar(Long id_cliente) {
        obterPorId(id_cliente);
        repositorio.deleteById(id_cliente);
    }
    
}
