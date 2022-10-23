package br.com.serratec.ecommerce.services;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.serratec.ecommerce.domains.Pedido;
import br.com.serratec.ecommerce.dto.PedidoRequestDTO;
import br.com.serratec.ecommerce.dto.PedidoResponseDTO;
import br.com.serratec.ecommerce.exceptions.ResourceNotFoundException;
import br.com.serratec.ecommerce.repositories.PedidoRepository;
import br.com.serratec.ecommerce.utils.NullAwareBeanUtilsBean;

@Service
public class PedidoService {
    
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

    public PedidoResponseDTO cadastrar(PedidoRequestDTO pedido) {
        
        //converte DTO para Entidade
        var pedidoModel = mapper.map(pedido, Pedido.class);

		pedidoRepository.save(pedidoModel);
        
        //converte Entidade para DTO
        var response = mapper.map(pedidoModel, PedidoResponseDTO.class);
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
