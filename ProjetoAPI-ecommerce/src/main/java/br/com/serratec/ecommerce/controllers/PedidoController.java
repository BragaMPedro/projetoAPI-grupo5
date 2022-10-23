package br.com.serratec.ecommerce.controllers;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.serratec.ecommerce.dto.PedidoRequestDTO;
import br.com.serratec.ecommerce.dto.PedidoResponseDTO;
import br.com.serratec.ecommerce.services.PedidoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/pedidos")
public class PedidoController{
    
    @Autowired
    private PedidoService pedidoService;

    @GetMapping
    @ApiOperation(value="Lista todos os pedidos", notes="Listagem de Pedidos")
	@ApiResponses(value= {
	@ApiResponse(code=200, message="Retorna todos pedidos"),
	@ApiResponse(code=401, message="Erro de autenticação"),
	@ApiResponse(code=403, message="Não há permissão para acessar o recurso"),
	@ApiResponse(code=404, message="Recurso não encontrado"),
	@ApiResponse(code=505, message="Exceção interna da aplicação")  })
    public ResponseEntity<List<PedidoResponseDTO>> getAll() {
       
        return ResponseEntity.ok(pedidoService.obterTodos());
    }

    @GetMapping("/{id}")
    @ApiOperation(value="Retorna um pedido", notes="Retorna um pedido utilizando seu Id")
	@ApiResponses(value= {
	@ApiResponse(code=200, message="Retorna um pedido"),
	@ApiResponse(code=401, message="Erro de autenticação"),
	@ApiResponse(code=403, message="Não há permissão para acessar o recurso"),
	@ApiResponse(code=404, message="Recurso não encontrado"),
	@ApiResponse(code=505, message="Exceção interna da aplicação")  })
    public ResponseEntity<PedidoResponseDTO> getById(@PathVariable Long Id) {
       
        var pedidoDTO = pedidoService.obterById(Id);
        return ResponseEntity.ok(pedidoDTO.get());
    }

    @PostMapping
    @ApiOperation(value="Insere os dados de um Pedido", notes="Inserir pedido")
	@ApiResponses(value= {
	@ApiResponse(code=200, message="Pedido adicionado"),
	@ApiResponse(code=401, message="Erro de autenticação"),
	@ApiResponse(code=403, message="Não há permissão para acessar o recurso"),
	@ApiResponse(code=404, message="Recurso não encontrado"),
	@ApiResponse(code=505, message="Exceção interna da aplicação")  })
    public ResponseEntity<PedidoResponseDTO> post(@Valid @RequestBody PedidoRequestDTO pedido) throws IllegalAccessException, InvocationTargetException {

        var contaDTO = pedidoService.cadastrar(pedido);
		return new ResponseEntity<>(contaDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @ApiOperation(value="Atualiza um Pedido por completo", notes="Atualizar pedido")
	@ApiResponses(value= {
	@ApiResponse(code=200, message="Pedido atualizado"),
	@ApiResponse(code=401, message="Erro de autenticação"),
	@ApiResponse(code=403, message="Não há permissão para acessar o recurso"),
	@ApiResponse(code=404, message="Recurso não encontrado"),
	@ApiResponse(code=505, message="Exceção interna da aplicação")  })
    public ResponseEntity<PedidoResponseDTO> put(@PathVariable Long id, @Valid @RequestBody PedidoRequestDTO pedido) {

        var pedidoDTO = pedidoService.atualizar(id, pedido);
		return new ResponseEntity<>(pedidoDTO, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value="Remove um pedido", notes="Remover Pedido")
	@ApiResponses(value= {
	@ApiResponse(code=200, message="Pedido Removido"),
	@ApiResponse(code=401, message="Erro de autenticação"),
	@ApiResponse(code=403, message="Não há permissão para acessar o recurso"),
	@ApiResponse(code=404, message="Recurso não encontrado"),
	@ApiResponse(code=505, message="Exceção interna da aplicação")	})
    public ResponseEntity<?> deletar(@PathVariable Long id) {
       
        pedidoService.deletar(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/{id}")
    @ApiOperation(value="Atualiza dados de um Pedido", notes="Atualizar Pedido")
	@ApiResponses(value= {
	@ApiResponse(code=200, message="Dado(s) Atualizado(s)"),
	@ApiResponse(code=401, message="Erro de autenticação"),
	@ApiResponse(code=403, message="Não há permissão para acessar o recurso"),
	@ApiResponse(code=404, message="Recurso não encontrado"),
	@ApiResponse(code=505, message="Exceção interna da aplicação")	})
    public ResponseEntity<PedidoResponseDTO> patch(@PathVariable Long id, @Valid @RequestBody PedidoRequestDTO pedido)
            throws IllegalAccessException, InvocationTargetException {
       
        var pedidoDTO = pedidoService.atualizarParcial(id, pedido);
		return new ResponseEntity<>(pedidoDTO, HttpStatus.PARTIAL_CONTENT);
    }
}
