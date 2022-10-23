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

import br.com.serratec.ecommerce.dto.ProdutoRequestDTO;
import br.com.serratec.ecommerce.dto.ProdutoResponseDTO;
import br.com.serratec.ecommerce.services.ProdutoService;



@RestController 
@RequestMapping("/produtos")
public class ProdutoController {

	@Autowired
	private ProdutoService servico;
	
	@GetMapping
	public ResponseEntity<List<ProdutoResponseDTO>> obterTodos(){
		
		List<ProdutoResponseDTO> lista = servico.obterTodos();
		return ResponseEntity.ok(lista);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ProdutoResponseDTO> obterPorId(@PathVariable Long id_produto){
		
		var optProduto = servico.obterPorId(id_produto);
		return ResponseEntity.ok(optProduto.get());
	}
	
	@PostMapping 
	public ResponseEntity<ProdutoResponseDTO> cadastrar(@Valid @RequestBody ProdutoRequestDTO produto) {
		
		
		var produtoDTO = servico.cadastrar(produto);
		return new ResponseEntity<>(produtoDTO, HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ProdutoResponseDTO> atualizar(@PathVariable Long id_produto,@Valid @RequestBody ProdutoRequestDTO produto) {
		
		var produtoDTO = servico.atualizar(id_produto, produto);
		return new ResponseEntity<>(produtoDTO, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletar(@PathVariable Long id_produto) {
		servico.deletar(id_produto);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
