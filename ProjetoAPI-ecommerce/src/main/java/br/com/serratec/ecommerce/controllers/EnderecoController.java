package br.com.serratec.ecommerce.controllers;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.serratec.ecommerce.dto.enderecoDTOs.EnderecoRequestDTO;
import br.com.serratec.ecommerce.dto.enderecoDTOs.EnderecoResponseDTO;
import br.com.serratec.ecommerce.services.EnderecoService;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController{
    
    @Autowired
    private EnderecoService enderecoService;
    

    public ResponseEntity<List<EnderecoResponseDTO>> getAll() {
       
        return ResponseEntity.ok(enderecoService.obterTodos());
    }

    public ResponseEntity<EnderecoResponseDTO> getById(Long Id) {
       
        var enderecoDTO = enderecoService.obterById(Id);
        return ResponseEntity.ok(enderecoDTO.get());
    }

    public ResponseEntity<EnderecoResponseDTO> post(@Valid EnderecoRequestDTO endereco) {
        
        var contaDTO = enderecoService.cadastrar(endereco);
		return new ResponseEntity<>(contaDTO, HttpStatus.CREATED);
    }

    public ResponseEntity<EnderecoResponseDTO> put(Long id, @Valid EnderecoRequestDTO endereco) {
        
        var enderecoDTO = enderecoService.atualizar(id, endereco);
		return new ResponseEntity<>(enderecoDTO, HttpStatus.CREATED);
    }

    public ResponseEntity<?> deletar(Long id) {
       
        enderecoService.deletar(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<EnderecoResponseDTO> patch(Long id, @Valid EnderecoRequestDTO endereco)
            throws IllegalAccessException, InvocationTargetException {
       
        var enderecoDTO = enderecoService.atualizarParcial(id, endereco);
		return new ResponseEntity<>(enderecoDTO, HttpStatus.PARTIAL_CONTENT);
    }
}
