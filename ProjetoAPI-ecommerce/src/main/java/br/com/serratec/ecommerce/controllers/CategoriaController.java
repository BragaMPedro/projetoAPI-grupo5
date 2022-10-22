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

import br.com.serratec.ecommerce.domains.Categoria;
import br.com.serratec.ecommerce.services.CategoriaService;
import br.com.serratec.ecommerce.utils.Helper;
import io.swagger.annotations.ApiResponse;

@RestController 
@RequestMapping("/api/categorias") 
public class CategoriaController {

	@Autowired
	private CategoriaService categoriaservice;
	
	@GetMapping("/")
	public ResponseEntity<List<Categoria>> obterTodos(){
		
		List<Categoria> body = categoriaservice.obterTodos();
		return new ResponseEntity<List<Categoria>>(body, HttpStatus.OK); 
	}
	
	@GetMapping("/{id_categoria}")
	public ResponseEntity<Categoria> obterPorid_categoria(@PathVariable Integer id_categoria){
		
		Optional<Categoria> optCategoria = categoriaservice.obterPorid_categoria(id_categoria);
		return ResponseEntity.ok(optCategoria.get()); 
	}
	
	@PostMapping("/cadastrar") 
	public ResponseEntity<ApiResponse> cadastrar_categoria(@Valid @RequestBody Categoria categoria) {
		if (Helper.notNull(categoriaservice.leia_categoria(categoria.getNome_categoria()))) {
			return new ResponseEntity<ApiResponse>(new ApiResponse(false, "A categoria ja existe"), HttpStatus.CONFLICT );
		//Para a ApiResponse funcionar é preciso criar um pacote common com uma classe ApiResponse.
		}
		categoriaservice.cadastrar_categoria(categoria);
			return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Categoria criada!"), HttpStatus.CREATED);
			 
	}
	
	@PutMapping("/atualizar/{id_categoria}")
	public ResponseEntity<ApiResponse> atualizar_categoria(@PathVariable ("id_categoria") Integer id_categoria, @Valid @RequestBody Categoria categoria) {
		if (Helper.notNull(categoriaservice.leia_categoria(id_categoria))) {
			categoriaservice.atualizar_categoria(id_categoria, categoria);
			return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Categoria atualizada!"), HttpStatus.OK);
		}
		
		return new ResponseEntity<ApiResponse>(new ApiResponse(false, "A categoria não existe"), HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("/deletar/{id_categoria}")
	public ResponseEntity<?> deletar_categoria(@PathVariable Integer id_categoria) {
		categoriaservice.deletar_categoria(id_categoria);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT); 
	}
}