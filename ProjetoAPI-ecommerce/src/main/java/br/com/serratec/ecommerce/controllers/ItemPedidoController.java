package br.com.serratec.ecommerce.controllers;

import java.util.List;

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

import br.com.serratec.ecommerce.dto.ItemPedidoRequestDTO;
import br.com.serratec.ecommerce.dto.ItemPedidoResponseDTO;
import br.com.serratec.ecommerce.services.ItemPedidoService;

@RestController 
@RequestMapping("/itemPedidos")
public class ItemPedidoController {

	@Autowired
	private ItemPedidoService servico;
	
	@GetMapping
	public ResponseEntity<List<ItemPedidoResponseDTO>> obterTodos(){
		
		List<ItemPedidoResponseDTO> lista = servico.obterTodos();
		return ResponseEntity.ok(lista);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ItemPedidoResponseDTO> obterPorId(@PathVariable Long id_itemPedido){
		
		var optItemPedido = servico.obterPorId(id_itemPedido);
		return ResponseEntity.ok(optItemPedido.get());
	}
	
	@PostMapping 
	public ResponseEntity<ItemPedidoResponseDTO> cadastrar(@Valid @RequestBody ItemPedidoRequestDTO itemPedido) {
		
		
		var itemPedidoDTO = servico.cadastrar(itemPedido);
		return new ResponseEntity<>(itemPedidoDTO, HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ItemPedidoResponseDTO> atualizar(@PathVariable Long id_itemPedido,@Valid @RequestBody ItemPedidoRequestDTO itemPedido) {
		
		var itemPedidoDTO = servico.atualizar(id_itemPedido, itemPedido);
		return new ResponseEntity<>(itemPedidoDTO, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletar(@PathVariable Long id_itemPedido) {
		servico.deletar(id_itemPedido);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}

