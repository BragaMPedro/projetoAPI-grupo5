package br.com.serratec.ecommerce.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.serratec.ecommerce.dto.ClienteRequestDTO;
import br.com.serratec.ecommerce.dto.ClienteResponseDTO;
import br.com.serratec.ecommerce.services.ClienteService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping ("/cliente")
public class ClienteController {

    @Autowired
    private ClienteService servico;
    
    @GetMapping
    @ApiOperation(value="Lista todos os clientes", notes="Listagem de Clientes")
	@ApiResponses(value= {
	@ApiResponse(code=200, message="Retorna todos clientes"),
	@ApiResponse(code=401, message="Erro de autenticação"),
	@ApiResponse(code=403, message="Não há permissão para acessar o recurso"),
	@ApiResponse(code=404, message="Recurso não encontrado"),
	@ApiResponse(code=505, message="Exceção interna da aplicação")  })
    public ResponseEntity<List<ClienteResponseDTO>> obterTodos(){
        
        List<ClienteResponseDTO> lista = servico.obterTodos();
        return ResponseEntity.ok(lista);
    }
    
    @GetMapping("/{id_cliente}")
    @ApiOperation(value="Retorna um cliente", notes="Retorna um cliente utilizando seu Id")
	@ApiResponses(value= {
	@ApiResponse(code=200, message="Retorna um cliente"),
	@ApiResponse(code=401, message="Erro de autenticação"),
	@ApiResponse(code=403, message="Não há permissão para acessar o recurso"),
	@ApiResponse(code=404, message="Recurso não encontrado"),
	@ApiResponse(code=505, message="Exceção interna da aplicação")  })
    public ResponseEntity<ClienteResponseDTO> obterPorId(@PathVariable Long id_cliente){
        
        var optCliente = servico.obterPorId(id_cliente);
        return ResponseEntity.ok(optCliente.get());
    }
    
    @PostMapping 
    @ApiOperation(value="Insere os dados de um Cliente", notes="Inserir cliente")
	@ApiResponses(value= {
	@ApiResponse(code=200, message="Cliente adicionado"),
	@ApiResponse(code=401, message="Erro de autenticação"),
	@ApiResponse(code=403, message="Não há permissão para acessar o recurso"),
	@ApiResponse(code=404, message="Recurso não encontrado"),
	@ApiResponse(code=505, message="Exceção interna da aplicação")  })
    public ResponseEntity<ClienteResponseDTO> cadastrar(@Valid @RequestBody ClienteRequestDTO cliente) {
    	
    	
        var clienteDTO = servico.cadastrar(cliente);
        return new ResponseEntity<>(clienteDTO, HttpStatus.CREATED);
        
    }
    
    @PutMapping("/{id_cliente}")
    @ApiOperation(value="Atualiza um Cliente por completo", notes="Atualizar cliente")
	@ApiResponses(value= {
	@ApiResponse(code=200, message="Cliente atualizado"),
	@ApiResponse(code=401, message="Erro de autenticação"),
	@ApiResponse(code=403, message="Não há permissão para acessar o recurso"),
	@ApiResponse(code=404, message="Recurso não encontrado"),
	@ApiResponse(code=505, message="Exceção interna da aplicação")  })
    public ResponseEntity<ClienteResponseDTO> atualizar(@PathVariable Long id_cliente, @Valid @RequestBody ClienteRequestDTO cliente) {
    	
    	var clienteDTO = servico.atualizar(id_cliente, cliente);
        return new ResponseEntity<>(clienteDTO, HttpStatus.CREATED);
        
    }
    
    @DeleteMapping("/{id_cliente}")
    @ApiOperation(value="Remove um cliente", notes="Remover Cliente")
	@ApiResponses(value= {
	@ApiResponse(code=200, message="Cliente Removido"),
	@ApiResponse(code=401, message="Erro de autenticação"),
	@ApiResponse(code=403, message="Não há permissão para acessar o recurso"),
	@ApiResponse(code=404, message="Recurso não encontrado"),
	@ApiResponse(code=505, message="Exceção interna da aplicação")	})
    public ResponseEntity<?> deletar(@PathVariable Long id_cliente) {
        servico.deletar(id_cliente);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @PatchMapping("/{id_cliente}")
    @ApiOperation(value="Atualiza um Cliente por completo", notes="Atualizar cliente")
	@ApiResponses(value= {
	@ApiResponse(code=200, message="Cliente atualizado"),
	@ApiResponse(code=401, message="Erro de autenticação"),
	@ApiResponse(code=403, message="Não há permissão para acessar o recurso"),
	@ApiResponse(code=404, message="Recurso não encontrado"),
	@ApiResponse(code=505, message="Exceção interna da aplicação")  })
    public ResponseEntity<ClienteResponseDTO> atualizarParcial(@PathVariable Long id_cliente, @Valid @RequestBody ClienteRequestDTO cliente) {
    	
    	var clienteDTO = servico.atualizarParcial(id_cliente, cliente);
        return new ResponseEntity<>(clienteDTO, HttpStatus.CREATED);
        
    }
   
  }
