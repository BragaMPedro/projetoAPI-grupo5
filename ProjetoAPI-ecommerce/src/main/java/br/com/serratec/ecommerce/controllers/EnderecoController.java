package br.com.serratec.ecommerce.controllers;

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
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController{
    
    @Autowired
    private EnderecoService enderecoService;

    @GetMapping
    @ApiOperation(value="Lista todos os endereços", notes="Listagem de endereços dos Clientes")
	@ApiResponses(value= {
	@ApiResponse(code=200, message="Retorna todos endereços"),
	@ApiResponse(code=401, message="Erro de autenticação"),
	@ApiResponse(code=403, message="Não há permissão para acessar o recurso"),
	@ApiResponse(code=404, message="Recurso não encontrado"),
	@ApiResponse(code=505, message="Exceção interna da aplicação")  })
    public ResponseEntity<List<EnderecoResponseDTO>> getAll() {
       
        return ResponseEntity.ok(enderecoService.obterTodos());
    }

    @GetMapping("/{id}")
    @ApiOperation(value="Retorna um endereço", notes="Retorna um endereço utilizando seu Id")
	@ApiResponses(value= {
	@ApiResponse(code=200, message="Retorna um endereço"),
	@ApiResponse(code=401, message="Erro de autenticação"),
	@ApiResponse(code=403, message="Não há permissão para acessar o recurso"),
	@ApiResponse(code=404, message="Recurso não encontrado"),
	@ApiResponse(code=505, message="Exceção interna da aplicação")  })
    public ResponseEntity<EnderecoResponseDTO> getById(@PathVariable Long Id) {
       
        var enderecoDTO = enderecoService.obterById(Id);
        return ResponseEntity.ok(enderecoDTO.get());
    }

    @PostMapping
    @ApiOperation(value="Insere os dados de um Endereço", notes="Inserir endereço")
	@ApiResponses(value= {
	@ApiResponse(code=200, message="Endereço adicionado"),
	@ApiResponse(code=401, message="Erro de autenticação"),
	@ApiResponse(code=403, message="Não há permissão para acessar o recurso"),
	@ApiResponse(code=404, message="Recurso não encontrado"),
	@ApiResponse(code=505, message="Exceção interna da aplicação")  })
    public ResponseEntity<EnderecoResponseDTO> post(@Valid @RequestBody EnderecoRequestDTO endereco) {

       ViaCep viaCep = getEnderecoByCep(endereco.getCep());

        var contaDTO = enderecoService.cadastrar(endereco, viaCep);
		return new ResponseEntity<>(contaDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @ApiOperation(value="Atualiza um Endereço por completo", notes="Atualizar endereço")
	@ApiResponses(value= {
	@ApiResponse(code=200, message="Endereço atualizado"),
	@ApiResponse(code=401, message="Erro de autenticação"),
	@ApiResponse(code=403, message="Não há permissão para acessar o recurso"),
	@ApiResponse(code=404, message="Recurso não encontrado"),
	@ApiResponse(code=505, message="Exceção interna da aplicação")  })
    public ResponseEntity<EnderecoResponseDTO> put(@PathVariable Long id, @Valid @RequestBody EnderecoRequestDTO endereco) {

        ViaCep viaCep = getEnderecoByCep(endereco.getCep());

        var enderecoDTO = enderecoService.atualizar(id, endereco, viaCep);
		return new ResponseEntity<>(enderecoDTO, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value="Remove um endereço", notes="Remover Endereço")
	@ApiResponses(value= {
	@ApiResponse(code=200, message="Endereço Removido"),
	@ApiResponse(code=401, message="Erro de autenticação"),
	@ApiResponse(code=403, message="Não há permissão para acessar o recurso"),
	@ApiResponse(code=404, message="Recurso não encontrado"),
	@ApiResponse(code=505, message="Exceção interna da aplicação")	})
    public ResponseEntity<?> deletar(@PathVariable Long id) {
       
        enderecoService.deletar(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/{id}")
    @ApiOperation(value="Atualiza dados de um Endereço", notes="Atualizar Endereço")
	@ApiResponses(value= {
	@ApiResponse(code=200, message="Dado(s) Atualizado(s)"),
	@ApiResponse(code=401, message="Erro de autenticação"),
	@ApiResponse(code=403, message="Não há permissão para acessar o recurso"),
	@ApiResponse(code=404, message="Recurso não encontrado"),
	@ApiResponse(code=505, message="Exceção interna da aplicação")	})
    public ResponseEntity<EnderecoResponseDTO> patch(@PathVariable Long id, @Valid @RequestBody EnderecoRequestDTO endereco)	{
       
        var enderecoDTO = enderecoService.atualizarParcial(id, endereco);
		return new ResponseEntity<>(enderecoDTO, HttpStatus.OK);
    }

    public ViaCep getEnderecoByCep(@RequestBody String cep){

        RestTemplate restTemplate = new RestTemplate();
		ViaCep viaCep = restTemplate.getForObject("http://viacep.com.br/ws/"+ cep +"/json/", ViaCep.class);

        return viaCep;
    }
}
