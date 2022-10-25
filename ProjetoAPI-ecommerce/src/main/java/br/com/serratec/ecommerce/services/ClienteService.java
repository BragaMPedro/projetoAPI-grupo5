package br.com.serratec.ecommerce.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.serratec.ecommerce.domains.Cliente;
import br.com.serratec.ecommerce.dto.ClienteRequestDTO;
import br.com.serratec.ecommerce.dto.ClienteResponseDTO;
import br.com.serratec.ecommerce.exceptions.ResourceNotFoundException;
import br.com.serratec.ecommerce.repositories.ClienteRepository;
import br.com.serratec.ecommerce.repositories.EnderecoRepository;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repositorio;

    @Autowired
    @SuppressWarnings("all")
    private EnderecoRepository enderecoRepository;

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
        
        var clienteModel = mapper.map(cliente, Cliente.class);
		repositorio.save(clienteModel);
        
        //converte Entidade para DTO
        var response = mapper.map(clienteModel, ClienteResponseDTO.class);

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

    // public ClienteResponseDTO cadastrar(ClienteRequestDTO cliente) {

    //     var clienteModel = mapper.map(cliente, Cliente.class);
    //     var clienteEndList = clienteModel.getEnderecos();
    //     List<EnderecoResponseDTO> enderecoResponse= new ArrayList<EnderecoResponseDTO>();

    //     for (EnderecoRequestDTO enderecoDTO : cliente.getEnderecos()) {

    //         ViaCep viaCep = enderecoController.getEnderecoByCep(enderecoDTO.getCep());
         
    //         var enderecoModel = mapper.map(enderecoDTO, Endereco.class);
    //         enderecoModel.viaCepEnderecoUniter(viaCep);
            
    //         enderecoModel.setCliente(clienteModel);
    //         enderecoRepository.save(enderecoModel);
            
    //         clienteEndList.add(enderecoModel);
    //         enderecoResponse.add(mapper.map(enderecoModel, EnderecoResponseDTO.class));
    //     }

    //     repositorio.save(clienteModel);
    //     var response = mapper.map(clienteModel, ClienteResponseDTO.class);

    //     response.setEnderecos(enderecoResponse);

    //     var destinatarios = new ArrayList<String>();
    //     destinatarios.add("turma05serratec@gmail.com");
    //     destinatarios.add("pedrobmagalhaes95@gmail.com");

    //     MensagemEmail email = new MensagemEmail(
    //     "Nova conta criada.",
    //     "<h1 style=\"color:red\"> Conta criada com Sucesso! </h1>",
    //     "turma05serratec@gmail.com",
    //     destinatarios);

    //     emailService.enviarEmail(email);

    //     return response;
    // }

}
