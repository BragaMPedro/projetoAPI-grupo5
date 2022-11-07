package br.com.serratec.ecommerce.services;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.serratec.ecommerce.domains.Cliente;
import br.com.serratec.ecommerce.domains.Endereco;
import br.com.serratec.ecommerce.domains.MensagemEmail;
import br.com.serratec.ecommerce.dto.ClienteRequestDTO;
import br.com.serratec.ecommerce.dto.ClienteResponseDTO;
import br.com.serratec.ecommerce.dto.enderecoDTOs.EnderecoRequestDTO;
import br.com.serratec.ecommerce.dto.enderecoDTOs.EnderecoResponseDTO;
import br.com.serratec.ecommerce.exceptions.ResourceBadRequestException;
import br.com.serratec.ecommerce.exceptions.ResourceNotFoundException;
import br.com.serratec.ecommerce.repositories.ClienteRepository;
import br.com.serratec.ecommerce.utils.NullAwareBeanUtilsBean;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repositorio;

    @Autowired
    private EnderecoService enderecoService;

    @Autowired
	private NullAwareBeanUtilsBean beanUtilsBean;

    @Autowired
    @SuppressWarnings("all")
    private MailService emailService;

    private ModelMapper mapper = new ModelMapper();

    public List<ClienteResponseDTO> obterTodos() {
        List<Cliente> lista = repositorio.findAll();

        return lista.stream()
                .map(cliente -> mapper.map(cliente, ClienteResponseDTO.class))
                .collect(Collectors.toList());
    }

    public Optional<ClienteResponseDTO> obterPorId(Long id_cliente) {

        var optCliente = repositorio.findById(id_cliente);

        if (optCliente.isEmpty()) {
            throw new ResourceNotFoundException("Não foi possivel encontrar o Cliente " + id_cliente);
        }

        ClienteResponseDTO dto = mapper.map(optCliente.get(), ClienteResponseDTO.class);

        return Optional.of(dto);
    }

    public ClienteResponseDTO cadastrar(ClienteRequestDTO cliente) {

        //Convertendo DTO em Entidade
        var clienteModel = mapper.map(cliente, Cliente.class);
        
        if(validarCpf(clienteModel) == false){
            throw new ResourceBadRequestException("Já existe uma conta com esse CPF");
        }

        //Array de EndereçosReponse para Settar pós-"for"
        List<EnderecoResponseDTO> enderecoResponseList= new ArrayList<EnderecoResponseDTO>();

        //Array de Endereços para Settar endereço completo pós tratamento viaCep
        List<Endereco> clienteEnderecosList = new ArrayList<Endereco>();


        for (EnderecoRequestDTO enderecoDTO : cliente.getEnderecos()) {

            //Cadastra Endereço e add na Lista enderecoRepsonse
            var enderecoResponse = enderecoService.cadastrarComCliente(clienteModel, enderecoDTO);
            enderecoResponseList.add(enderecoResponse);
         
            //Converte (DTO -> Entity) Endereço e setta Cliente
            var enderecoModel = mapper.map(enderecoResponse, Endereco.class);

            clienteEnderecosList.add(enderecoModel);
        }

        //Setta endereços completos na Entidade
        clienteModel.setEnderecos(clienteEnderecosList);
        repositorio.save(clienteModel);

        //Prepara Response e Setta endereços completos no DTO
        var response = mapper.map(clienteModel, ClienteResponseDTO.class);
        response.setEnderecos(enderecoResponseList);

        // var destinatarios = new ArrayList<String>();
        // destinatarios.add("turma05serratec@gmail.com");
        // destinatarios.add("pedrobmagalhaes95@gmail.com");
        // destinatarios.add("isis.reis.castro@gmail.com");
        // //destinatarios.add(clienteModel.getEmail());

        // MensagemEmail email = new MensagemEmail(
        // "API ALERT - grupo 5",
        // "<h1 style=\"color:red\"> Atenção! Weberson está testando sua API. Acabou de fazer um POST de cliente! </h1>"+
        // "</br></br>"+
        // "<p> estamos de olho </p>",
        // "grupo5maneiro@gmail.com",
        // destinatarios);

        // emailService.enviarEmail(email);

        return response;
    }

    public ClienteResponseDTO atualizar(Long id_cliente, ClienteRequestDTO cliente) {

        obterPorId(id_cliente);

        var clienteModel = mapper.map(cliente, Cliente.class);

        clienteModel.setId_cliente(id_cliente);
        repositorio.save(clienteModel);

        return mapper.map(clienteModel, ClienteResponseDTO.class);
    }

    public void deletar(Long id_cliente) {
        obterPorId(id_cliente);
        repositorio.deleteById(id_cliente);
    }

    public ClienteResponseDTO atualizarParcial(Long id, Cliente cliente)   {
       
        //Objeto do banco de dados
        var clienteDB = obterPorId(id);

         //Conversão do DTOrequest para Entidade
		var clienteDBmodel = mapper.map(clienteDB.get(), Cliente.class);
        
        //realiza método em si
        try {
            beanUtilsBean.copyProperties(clienteDBmodel, cliente);
        } catch (IllegalAccessException e) {
            
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            
            e.printStackTrace();
        }

		repositorio.save(clienteDBmodel);
		
        //converte Entidade em DTO e retorna
        return mapper.map(clienteDBmodel, ClienteResponseDTO.class);
    }

    private boolean validarCpf(Cliente cliente) {
        var clientesList = repositorio.findAll();
        
        for (Cliente clienteDB : clientesList) {
            if(cliente.getCpf() == clienteDB.getCpf()){
                return false;
            }
        }
        return true;
    }

}
