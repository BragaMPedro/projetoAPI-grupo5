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
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;



@RestController 
@RequestMapping("/produtos")
public class ProdutoController {

	@Autowired
	private ProdutoService servico;
	
	@GetMapping
	@ApiOperation(value="Lista todos os produtos", notes="Listagem de produtos do estoque")
	@ApiResponses(value= {
	@ApiResponse(code=200, message="Retorna todos produtos"),
	@ApiResponse(code=401, message="Erro de autenticação"),
	@ApiResponse(code=403, message="Não há permissão para acessar o recurso"),
	@ApiResponse(code=404, message="Recurso não encontrado"),
	@ApiResponse(code=505, message="Exceção interna da aplicação")  })
	public ResponseEntity<List<ProdutoResponseDTO>> obterTodos(){
		
		List<ProdutoResponseDTO> lista = servico.obterTodos();
		return ResponseEntity.ok(lista);
	}
	
	@GetMapping("/{id_produto}")
	@ApiOperation(value="Retorna um produto", notes="Retorna um produto utilizando seu Id")
	@ApiResponses(value= {
	@ApiResponse(code=200, message="Retorna um produto"),
	@ApiResponse(code=401, message="Erro de autenticação"),
	@ApiResponse(code=403, message="Não há permissão para acessar o recurso"),
	@ApiResponse(code=404, message="Recurso não encontrado"),
	@ApiResponse(code=505, message="Exceção interna da aplicação")  })
	public ResponseEntity<ProdutoResponseDTO> obterPorId(@PathVariable Long id_produto){
		
		var optProduto = servico.obterPorId(id_produto);
		return ResponseEntity.ok(optProduto.get());
	}
	
	@PostMapping
	@ApiOperation(value="Insere os dados de um Produto", notes="Inserir produto")
	@ApiResponses(value= {
	@ApiResponse(code=200, message="Produto adicionado"),
	@ApiResponse(code=401, message="Erro de autenticação"),
	@ApiResponse(code=403, message="Não há permissão para acessar o recurso"),
	@ApiResponse(code=404, message="Recurso não encontrado"),
	@ApiResponse(code=505, message="Exceção interna da aplicação")  })
	public ResponseEntity<ProdutoResponseDTO> cadastrar(@Valid @RequestBody ProdutoRequestDTO produto) {
		
		
		var produtoDTO = servico.cadastrar(produto);
		return new ResponseEntity<>(produtoDTO, HttpStatus.CREATED);
	}
	
	@PutMapping("/{id_produto}")
	@ApiOperation(value="Atualiza um Produto por completo", notes="Atualizar produto")
	@ApiResponses(value= {
	@ApiResponse(code=200, message="Produto atualizado"),
	@ApiResponse(code=401, message="Erro de autenticação"),
	@ApiResponse(code=403, message="Não há permissão para acessar o recurso"),
	@ApiResponse(code=404, message="Recurso não encontrado"),
	@ApiResponse(code=505, message="Exceção interna da aplicação")  })
	public ResponseEntity<ProdutoResponseDTO> atualizar(@PathVariable Long id_produto, @Valid @RequestBody ProdutoRequestDTO produto) {
		
		var produtoDTO = servico.atualizar(id_produto, produto);
		return new ResponseEntity<>(produtoDTO, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id_produto}")
	@ApiOperation(value="Remove um produto", notes="Remover Produto")
	@ApiResponses(value= {
	@ApiResponse(code=200, message="Produto Removido"),
	@ApiResponse(code=401, message="Erro de autenticação"),
	@ApiResponse(code=403, message="Não há permissão para acessar o recurso"),
	@ApiResponse(code=404, message="Recurso não encontrado"),
	@ApiResponse(code=505, message="Exceção interna da aplicação")	})
	public ResponseEntity<?> deletar(@PathVariable Long id_produto) {
		servico.deletar(id_produto);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
