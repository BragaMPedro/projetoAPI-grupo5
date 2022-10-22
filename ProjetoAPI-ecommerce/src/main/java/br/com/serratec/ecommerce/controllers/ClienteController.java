package br.com.serratec.ecommerce.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.serratec.ecommerce.domains.Cliente;
import br.com.serratec.ecommerce.services.ClienteService;


@RestController
@RequestMapping ("/cliente")
public class ClienteController {

    @Autowired
    private ClienteService servico;
    
    @GetMapping
    public ResponseEntity<List<Cliente>> obterTodos(){
        
        List<Cliente> lista = servico.obterTodos();
        return ResponseEntity.ok(lista);
    }
    
    @GetMapping("/{id_cliente}")
    public ResponseEntity<Cliente> obterPorId(@PathVariable Long id_cliente){
        
        Optional<Cliente> optCliente = servico.obterPorId(id_cliente);
        return ResponseEntity.ok(optCliente.get());
    }
    
    @PostMapping 
    public ResponseEntity<Cliente> cadastrar(@Valid @RequestBody Cliente cliente) {
        cliente = servico.cadastrar(cliente);
        return new ResponseEntity<>(cliente, HttpStatus.CREATED);
        
    }
    
    @PutMapping("/{id_cliente}")
    public ResponseEntity<Cliente> atualizar(@PathVariable Long id_cliente, @Valid @RequestBody Cliente cliente) {
        return ResponseEntity.ok(servico.atualizar(id_cliente, cliente));
        
    }
    
    @DeleteMapping("/{id_cliente}")
    public ResponseEntity<?> deletar(@PathVariable Long id_cliente) {
        servico.deletar(id_cliente);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
   
  }
