package br.com.serratec.ecommerce.controllers;

import java.util.List;
import java.util.Optional;

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

import br.com.serratec.ecommerce.domains.Categoria;
import br.com.serratec.ecommerce.services.CategoriaService;

@RestController 
@RequestMapping("/api/categorias") 
public class CategoriaController {

	@Autowired
	private CategoriaService categoriaservice;
	
	@GetMapping
	public ResponseEntity<List<Categoria>> obterTodos(){
		
		List<Categoria> lista = categoriaservice.obterTodos();
		return ResponseEntity.ok(lista); // 200
	}
	
	@GetMapping("/{id_categoria}")
	public ResponseEntity<Categoria> obterPorid_categoria(@PathVariable Integer id_categoria){
		
		Optional<Categoria> optCategoria = categoriaservice.obterPorid_categoria(id_categoria);
		return ResponseEntity.ok(optCategoria.get()); // 200
	}
	
	@PostMapping 
	public ResponseEntity<Categoria> cadastrar_categoria(@RequestBody Categoria categoria) {
		categoria = categoriaservice.cadastrar_categoria(categoria);
		return new ResponseEntity<>(categoria, HttpStatus.CREATED); // 201
	}
	
	@PutMapping("/{id_categoria}")
	public ResponseEntity<Categoria> atualizar_categoria(@PathVariable Integer id_categoria, @RequestBody Categoria categoria) {
		return ResponseEntity.ok(categoriaservice.atualizar_categoria(id_categoria, categoria)); // 200
	}
	
	@DeleteMapping("/{id_categoria}")
	public ResponseEntity<?> deletar_categoria(@PathVariable Integer id_categoria) {
		categoriaservice.deletar_categoria(id_categoria);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204
	}
}