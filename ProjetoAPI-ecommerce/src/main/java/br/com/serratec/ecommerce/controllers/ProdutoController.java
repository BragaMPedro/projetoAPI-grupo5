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

import br.com.serratec.ecommerce.domains.Produto;
import br.com.serratec.ecommerce.services.ProdutoService;



@RestController 
@RequestMapping
public class ProdutoController {

	@Autowired
	private ProdutoService servico;
	
	@GetMapping
	public ResponseEntity<List<Produto>> obterTodos(){
		
		List<Produto> lista = servico.obterTodos();
		return ResponseEntity.ok(lista);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Produto> obterPorId(@PathVariable Long id_produto){
		
		Optional<Produto> optProduto = servico.obterPorId(id_produto);
		return ResponseEntity.ok(optProduto.get());
	}
	
	@PostMapping 
	public ResponseEntity<Produto> cadastrar(@Valid @RequestBody Produto produto) {
		produto = servico.cadastrar(produto);
		return new ResponseEntity<>(produto, HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Produto> atualizar(@Valid @PathVariable Long id_produto, @RequestBody Produto produto) {
		return ResponseEntity.ok(servico.atualizar(id_produto, produto));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletar(@PathVariable Long id_produto) {
		servico.deletar(id_produto);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
