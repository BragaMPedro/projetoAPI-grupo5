package br.com.serratec.ecommerce.services;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.serratec.ecommerce.domains.ItemPedido;
import br.com.serratec.ecommerce.domains.Pedido;
import br.com.serratec.ecommerce.dto.ItemPedidoRequestDTO;
import br.com.serratec.ecommerce.dto.ItemPedidoResponseDTO;
import br.com.serratec.ecommerce.dto.PedidoRequestDTO;
import br.com.serratec.ecommerce.dto.PedidoResponseDTO;
import br.com.serratec.ecommerce.exceptions.ResourceNotFoundException;
import br.com.serratec.ecommerce.repositories.ClienteRepository;
import br.com.serratec.ecommerce.repositories.PedidoRepository;
import br.com.serratec.ecommerce.utils.NullAwareBeanUtilsBean;

@Service
public class PedidoService {
    
    @Autowired
	private ItemPedidoService itemPedidoService;
	
    @Autowired
	private ClienteRepository clienteRepository;
   
    @Autowired
    private PedidoRepository pedidoRepository;
    
    @Autowired
	private NullAwareBeanUtilsBean beanUtilsBean;

    private ModelMapper mapper = new ModelMapper();


    public List<PedidoResponseDTO> obterTodos() {
        List<Pedido> lista = pedidoRepository.findAll();

		return lista.stream()
				.map(pedido -> mapper.map(pedido, PedidoResponseDTO.class))
				.collect(Collectors.toList());
    }

    public Optional<PedidoResponseDTO> obterById(Long id) {
        var optPedido = pedidoRepository.findById(id);

		if(optPedido.isEmpty()){
			throw new ResourceNotFoundException("Não foi possível encontrar Endereço id " + id);
		}

		PedidoResponseDTO dto = mapper.map(optPedido.get(), PedidoResponseDTO.class);

		return Optional.of(dto);
    }

    public PedidoResponseDTO cadastrar(PedidoRequestDTO pedidoDTO) {

        //Convertendo DTO em Entidade
        var pedidoModel = mapper.map(pedidoDTO, Pedido.class);
        
        //Settando CLiente
        var cliente = clienteRepository.findById(pedidoDTO.getId_cliente()).get();
        pedidoModel.setCliente(cliente);
        
        //Array de ItensReponse para depois do "for"
        List<ItemPedidoResponseDTO>  itensResponse = new ArrayList<ItemPedidoResponseDTO>();
    
        for(ItemPedidoRequestDTO itemPedidoDTO : pedidoDTO.getItemPedido()){
            
            //Cadastra ItemPedido e add na Lista itensRepsonse
            var itemPedidoResponse = itemPedidoService.cadastrar(itemPedidoDTO);
            itensResponse.add(itemPedidoResponse);

            //Settando Pedido em ItemPedido (com conversão)
            var itemPedidoModel = mapper.map(itemPedidoDTO, ItemPedido.class);
            itemPedidoModel.setPedido(pedidoModel);
        }
        
        pedidoRepository.save(pedidoModel);	
        
        var response = mapper.map(pedidoModel, PedidoResponseDTO.class);
        
        response.setItemPedido(itensResponse);
        
        return response;
    
    }
    
    public PedidoResponseDTO atualizar(Long id, PedidoRequestDTO pedido) {
        
        //validação rápida
        obterById(id);
      
        //converte DTO em Entidade
        var pedidoModel = mapper.map(pedido, Pedido.class);
       
        //realiza o método em si
		pedidoModel.setId_pedido(id);
		pedidoRepository.save(pedidoModel);
      
        //converte Entidade em DTO e retorna
		return mapper.map(pedidoModel, PedidoResponseDTO.class);
    }

    public void deletar(Long id) {
        
        obterById(id);
		pedidoRepository.deleteById(id);
    }

    public PedidoResponseDTO atualizarParcial(Long id, PedidoRequestDTO pedido)
                throws IllegalAccessException, InvocationTargetException {
       
        //Objeto do banco de dados
        var pedidoDB = obterById(id);

         //Conversão do DTOrequest para Entidade
		var pedidoModel = mapper.map(pedido, Pedido.class);
		var pedidoDBmodel = mapper.map(pedidoDB.get(), Pedido.class);
        
        //realiza método em si
        beanUtilsBean.copyProperties(pedidoDBmodel, pedidoModel);

		pedidoRepository.save(pedidoDBmodel);
		
        //converte Entidade em DTO e retorna
        return mapper.map(pedidoDBmodel, PedidoResponseDTO.class);
    }

}
