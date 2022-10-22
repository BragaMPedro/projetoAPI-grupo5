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

@RestController
@RequestMapping("/pedidos")
public class PedidoController{
    
    @Autowired
    private PedidoService pedidoService;

    @GetMapping
    public ResponseEntity<List<PedidoResponseDTO>> getAll() {
       
        return ResponseEntity.ok(pedidoService.obterTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoResponseDTO> getById(@PathVariable Long Id) {
       
        var pedidoDTO = pedidoService.obterById(Id);
        return ResponseEntity.ok(pedidoDTO.get());
    }

    @PostMapping
    public ResponseEntity<PedidoResponseDTO> post(@Valid @RequestBody PedidoRequestDTO pedido) throws IllegalAccessException, InvocationTargetException {

        var contaDTO = pedidoService.cadastrar(pedido);
		return new ResponseEntity<>(contaDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PedidoResponseDTO> put(@PathVariable Long id, @Valid @RequestBody PedidoRequestDTO pedido) {

        var pedidoDTO = pedidoService.atualizar(id, pedido);
		return new ResponseEntity<>(pedidoDTO, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
       
        pedidoService.deletar(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<PedidoResponseDTO> patch(@PathVariable Long id, @Valid @RequestBody PedidoRequestDTO pedido)
            throws IllegalAccessException, InvocationTargetException {
       
        var pedidoDTO = pedidoService.atualizarParcial(id, pedido);
		return new ResponseEntity<>(pedidoDTO, HttpStatus.PARTIAL_CONTENT);
    }
}
