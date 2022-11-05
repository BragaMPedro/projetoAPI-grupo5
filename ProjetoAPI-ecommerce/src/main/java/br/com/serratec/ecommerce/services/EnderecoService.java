package br.com.serratec.ecommerce.services;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.serratec.ecommerce.domains.Cliente;
import br.com.serratec.ecommerce.domains.Endereco;
import br.com.serratec.ecommerce.domains.ViaCep;
import br.com.serratec.ecommerce.dto.enderecoDTOs.EnderecoRequestDTO;
import br.com.serratec.ecommerce.dto.enderecoDTOs.EnderecoResponseDTO;
import br.com.serratec.ecommerce.exceptions.ResourceNotFoundException;
import br.com.serratec.ecommerce.repositories.EnderecoRepository;
import br.com.serratec.ecommerce.utils.NullAwareBeanUtilsBean;

@Service
public class EnderecoService {
    
    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
	private NullAwareBeanUtilsBean beanUtilsBean;

    private ModelMapper mapper = new ModelMapper();


    public List<EnderecoResponseDTO> obterTodos() {
        List<Endereco> lista = enderecoRepository.findAll();

		return lista.stream()
				.map(endereco -> mapper.map(endereco, EnderecoResponseDTO.class))
				.collect(Collectors.toList());
    }

    public Optional<EnderecoResponseDTO> obterById(Long id) {
        var optEndereco = enderecoRepository.findById(id);

		if(optEndereco.isEmpty()){
			throw new ResourceNotFoundException("Não foi possível encontrar Endereço id " + id);
		}

		EnderecoResponseDTO dto = mapper.map(optEndereco.get(), EnderecoResponseDTO.class);

		return Optional.of(dto);
    }

    public EnderecoResponseDTO cadastrar(EnderecoRequestDTO endereco) {
        
        //Traz endereço completo via ViaCep
        ViaCep viaCep = getEnderecoByCep(endereco.getCep());

        //converte DTO para Entidade
        var enderecoModel = mapper.map(endereco, Endereco.class);

        //método em si (jeito longo)
        enderecoModel.viaCepEnderecoUniter(viaCep);

		enderecoRepository.save(enderecoModel);
        
        //converte Entidade para DTO
        var response = mapper.map(enderecoModel, EnderecoResponseDTO.class);
        return response;
    }

    public EnderecoResponseDTO cadastrarComCliente(Cliente cliente, EnderecoRequestDTO endereco) {
        
        //Traz endereço completo via ViaCep
        ViaCep viaCep = getEnderecoByCep(endereco.getCep());

        //converte DTO para Entidade
        var enderecoModel = mapper.map(endereco, Endereco.class);

        //método em si (jeito longo)
        enderecoModel.viaCepEnderecoUniter(viaCep);
        enderecoModel.setCliente(cliente);

		enderecoRepository.save(enderecoModel);
        
        //converte Entidade para DTO
        var response = mapper.map(enderecoModel, EnderecoResponseDTO.class);
        return response;
    }

    public EnderecoResponseDTO atualizar(Long id, EnderecoRequestDTO endereco) {
        
        //validação rápida
        obterById(id);
      
        //Traz endereço completo via ViaCep
        ViaCep viaCep = getEnderecoByCep(endereco.getCep());

        //converte DTO em Entidade
        var enderecoModel = mapper.map(endereco, Endereco.class);
       
        //realiza o método em si
		enderecoModel.setId(id);
        enderecoModel.viaCepEnderecoUniter(viaCep);
		enderecoRepository.save(enderecoModel);
      
        //converte Entidade em DTO e retorna
		return mapper.map(enderecoModel, EnderecoResponseDTO.class);
    }

    public void deletar(Long id) {
        
        obterById(id);
		enderecoRepository.deleteById(id);
    }

    public EnderecoResponseDTO atualizarParcial(Long id, EnderecoRequestDTO endereco)   {
       
        //Objeto do banco de dados
        var enderecoDB = obterById(id);

         //Conversão do DTOrequest para Entidade
		var enderecoModel = mapper.map(endereco, Endereco.class);
		var enderecoDBmodel = mapper.map(enderecoDB.get(), Endereco.class);
        
        //realiza método em si
        try {
            beanUtilsBean.copyProperties(enderecoDBmodel, enderecoModel);
        } catch (IllegalAccessException e) {
            
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            
            e.printStackTrace();
        }

		enderecoRepository.save(enderecoDBmodel);
		
        //converte Entidade em DTO e retorna
        return mapper.map(enderecoDBmodel, EnderecoResponseDTO.class);
    }

    public ViaCep getEnderecoByCep(String cep){

        RestTemplate restTemplate = new RestTemplate();
		ViaCep viaCep = restTemplate.getForObject("http://viacep.com.br/ws/"+ cep +"/json/", ViaCep.class);

        return viaCep;
    }
}
