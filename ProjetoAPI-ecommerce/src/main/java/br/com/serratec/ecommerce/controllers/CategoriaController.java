package br.com.serratec.ecommerce.controllers;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.serratec.ecommerce.domains.Categoria;
import br.com.serratec.ecommerce.services.CategoriaService;
import br.com.serratec.ecommerce.utils.CrudInterface;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/categorias")
public class CategoriaController implements CrudInterface<Categoria>{

    @Autowired
    private CategoriaService categoriaService;

    @Override
    @ApiOperation(value="Lista todos as categorias", notes="Listagem de categorias dos Produtos")
	@ApiResponses(value= {
	@ApiResponse(code=200, message="Retorna todos categorias"),
	@ApiResponse(code=401, message="Erro de autenticação"),
	@ApiResponse(code=403, message="Não há permissão para acessar o recurso"),
	@ApiResponse(code=404, message="Recurso não encontrado"),
	@ApiResponse(code=505, message="Exceção interna da aplicação")  })
    public ResponseEntity<List<Categoria>> getAll() {
        
        return ResponseEntity.ok(categoriaService.obterTodos());
    }

    @Override
    @ApiOperation(value="Retorna uma categoria", notes="Retorna um categoria utilizando seu Id")
	@ApiResponses(value= {
	@ApiResponse(code=200, message="Retorna uma categoria"),
	@ApiResponse(code=401, message="Erro de autenticação"),
	@ApiResponse(code=403, message="Não há permissão para acessar o recurso"),
	@ApiResponse(code=404, message="Recurso não encontrado"),
	@ApiResponse(code=505, message="Exceção interna da aplicação")  })
    public ResponseEntity<Categoria> getById(Long id) {
        
        var categoria = categoriaService.obterById(id);
        return ResponseEntity.ok(categoria.get());
    }

    @Override
    @ApiOperation(value="Insere os dados de uma Categoria", notes="Inserir categoria")
	@ApiResponses(value= {
	@ApiResponse(code=200, message="Categoria adicionada"),
	@ApiResponse(code=401, message="Erro de autenticação"),
	@ApiResponse(code=403, message="Não há permissão para acessar o recurso"),
	@ApiResponse(code=404, message="Recurso não encontrado"),
	@ApiResponse(code=505, message="Exceção interna da aplicação")  })
    public ResponseEntity<Categoria> post(@Valid Categoria categoria) {
       
        var cat = categoriaService.cadastrar(categoria);
		return new ResponseEntity<>(cat, HttpStatus.CREATED);
    }

    //PutMapping(/ID)
    @Override
    @ApiOperation(value="Atualiza uma Categoria por completo", notes="Atualizar categoria")
	@ApiResponses(value= {
	@ApiResponse(code=200, message="Categoria atualizada"),
	@ApiResponse(code=401, message="Erro de autenticação"),
	@ApiResponse(code=403, message="Não há permissão para acessar o recurso"),
	@ApiResponse(code=404, message="Recurso não encontrado"),
	@ApiResponse(code=505, message="Exceção interna da aplicação")  })
    public ResponseEntity<Categoria> put(Long id, @Valid Categoria categoria) {
        
        var cat = categoriaService.atualizar(id, categoria);
		return new ResponseEntity<>(cat, HttpStatus.CREATED);
    }

    //DeleteMapping(/ID)
    @Override
    @ApiOperation(value="Remove uma categoria", notes="Remover Categoria")
	@ApiResponses(value= {
	@ApiResponse(code=200, message="Categoria Removida"),
	@ApiResponse(code=401, message="Erro de autenticação"),
	@ApiResponse(code=403, message="Não há permissão para acessar o recurso"),
	@ApiResponse(code=404, message="Recurso não encontrado"),
	@ApiResponse(code=505, message="Exceção interna da aplicação")	})
    public ResponseEntity<?> deletar(Long id) {
        
        categoriaService.deletar(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //PatchMapping(/ID)
    @Override
    @ApiOperation(value="Atualiza dados de uma Categoria", notes="Atualizar Categoria")
	@ApiResponses(value= {
	@ApiResponse(code=200, message="Dado(s) Atualizado(s)"),
	@ApiResponse(code=401, message="Erro de autenticação"),
	@ApiResponse(code=403, message="Não há permissão para acessar o recurso"),
	@ApiResponse(code=404, message="Recurso não encontrado"),
	@ApiResponse(code=505, message="Exceção interna da aplicação")	})
    public ResponseEntity<Categoria> patch(Long id, @Valid Categoria categoria)
            throws IllegalAccessException, InvocationTargetException {
      
        var cat = categoriaService.atualizarParcial(id, categoria);
		return new ResponseEntity<>(cat, HttpStatus.PARTIAL_CONTENT);
    }
    
}
