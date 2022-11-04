package br.com.serratec.ecommerce.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.serratec.ecommerce.dto.ItemPedidoRequestDTO;
import br.com.serratec.ecommerce.dto.ItemPedidoResponseDTO;
import br.com.serratec.ecommerce.services.ItemPedidoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/itemPedidos")
public class ItemPedidoController {

	@Autowired
	private ItemPedidoService servico;
	
	@GetMapping
	@ApiOperation(value="Lista todos os itemPedidos", notes="Listagem de ItemPedidos")
	@ApiResponses(value= {
	@ApiResponse(code=200, message="Retorna todos itemPedidos"),
	@ApiResponse(code=401, message="Erro de autenticação"),
	@ApiResponse(code=403, message="Não há permissão para acessar o recurso"),
	@ApiResponse(code=404, message="Recurso não encontrado"),
	@ApiResponse(code=505, message="Exceção interna da aplicação")  })
	public ResponseEntity<List<ItemPedidoResponseDTO>> obterTodos(){
		
		List<ItemPedidoResponseDTO> lista = servico.obterTodos();
		return ResponseEntity.ok(lista);
	}
	
	@GetMapping("/{id_itemPedido}")
	@ApiOperation(value="Retorna um itemPedido", notes="Retorna um itemPedido utilizando seu Id")
	@ApiResponses(value= {
	@ApiResponse(code=200, message="Retorna um itemPedido"),
	@ApiResponse(code=401, message="Erro de autenticação"),
	@ApiResponse(code=403, message="Não há permissão para acessar o recurso"),
	@ApiResponse(code=404, message="Recurso não encontrado"),
	@ApiResponse(code=505, message="Exceção interna da aplicação")  })
	public ResponseEntity<ItemPedidoResponseDTO> obterPorId(@PathVariable Long id_itemPedido){
		
		var optItemPedido = servico.obterPorId(id_itemPedido);
		return ResponseEntity.ok(optItemPedido.get());
	}
	
	@PostMapping 
	@ApiOperation(value="Insere os dados de um ItemPedido", notes="Inserir itemPedido")
	@ApiResponses(value= {
	@ApiResponse(code=200, message="ItemPedido adicionado"),
	@ApiResponse(code=401, message="Erro de autenticação"),
	@ApiResponse(code=403, message="Não há permissão para acessar o recurso"),
	@ApiResponse(code=404, message="Recurso não encontrado"),
	@ApiResponse(code=505, message="Exceção interna da aplicação")  })
	public ResponseEntity<ItemPedidoResponseDTO> cadastrar(@Valid @RequestBody ItemPedidoRequestDTO itemPedido) {
		
		
		var itemPedidoDTO = servico.cadastrar(itemPedido);
		return new ResponseEntity<>(itemPedidoDTO, HttpStatus.CREATED);
	}
	
	@PutMapping("/{id_itemPedido}")
	@ApiOperation(value="Atualiza um ItemPedido por completo", notes="Atualizar itemPedido")
	@ApiResponses(value= {
	@ApiResponse(code=200, message="ItemPedido atualizado"),
	@ApiResponse(code=401, message="Erro de autenticação"),
	@ApiResponse(code=403, message="Não há permissão para acessar o recurso"),
	@ApiResponse(code=404, message="Recurso não encontrado"),
	@ApiResponse(code=505, message="Exceção interna da aplicação")  })
	public ResponseEntity<ItemPedidoResponseDTO> atualizar(@PathVariable Long id_itemPedido,@Valid @RequestBody ItemPedidoRequestDTO itemPedido) {
		
		var itemPedidoDTO = servico.atualizar(id_itemPedido, itemPedido);
		return new ResponseEntity<>(itemPedidoDTO, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id_itemPedido}")
	@ApiOperation(value="Remove um itemPedido", notes="Remover ItemPedido")
	@ApiResponses(value= {
	@ApiResponse(code=200, message="ItemPedido Removido"),
	@ApiResponse(code=401, message="Erro de autenticação"),
	@ApiResponse(code=403, message="Não há permissão para acessar o recurso"),
	@ApiResponse(code=404, message="Recurso não encontrado"),
	@ApiResponse(code=505, message="Exceção interna da aplicação")	})
	public ResponseEntity<?> deletar(@PathVariable Long id_itemPedido) {
		servico.deletar(id_itemPedido);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}

