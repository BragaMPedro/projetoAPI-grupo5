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
import org.springframework.web.client.RestTemplate;

import br.com.serratec.ecommerce.domains.ViaCep;
import br.com.serratec.ecommerce.dto.enderecoDTOs.EnderecoRequestDTO;
import br.com.serratec.ecommerce.dto.enderecoDTOs.EnderecoResponseDTO;
import br.com.serratec.ecommerce.services.EnderecoService;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController{
    
    @Autowired
    private EnderecoService enderecoService;

    @GetMapping
    public ResponseEntity<List<EnderecoResponseDTO>> getAll() {
       
        return ResponseEntity.ok(enderecoService.obterTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EnderecoResponseDTO> getById(@PathVariable Long Id) {
       
        var enderecoDTO = enderecoService.obterById(Id);
        return ResponseEntity.ok(enderecoDTO.get());
    }

    @GetMapping("/viacep")
    public ViaCep getEnderecoByCep(@RequestBody String cep){

        RestTemplate restTemplate = new RestTemplate();
		ViaCep viaCep = restTemplate.getForObject("http://viacep.com.br/ws/"+ cep +"/json/", ViaCep.class);

        return viaCep;
    }

    @PostMapping
    public ResponseEntity<EnderecoResponseDTO> post(@Valid @RequestBody EnderecoRequestDTO endereco) throws IllegalAccessException, InvocationTargetException {

       ViaCep viaCep = getEnderecoByCep(endereco.getCep());

        var contaDTO = enderecoService.cadastrar(endereco, viaCep);
		return new ResponseEntity<>(contaDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EnderecoResponseDTO> put(@PathVariable Long id, @Valid @RequestBody EnderecoRequestDTO endereco) {

        ViaCep viaCep = getEnderecoByCep(endereco.getCep());

        var enderecoDTO = enderecoService.atualizar(id, endereco, viaCep);
		return new ResponseEntity<>(enderecoDTO, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
       
        enderecoService.deletar(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<EnderecoResponseDTO> patch(@PathVariable Long id, @Valid @RequestBody EnderecoRequestDTO endereco)
            throws IllegalAccessException, InvocationTargetException {
       
        var enderecoDTO = enderecoService.atualizarParcial(id, endereco);
		return new ResponseEntity<>(enderecoDTO, HttpStatus.PARTIAL_CONTENT);
    }
}
