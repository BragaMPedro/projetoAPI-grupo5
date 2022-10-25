package br.com.serratec.ecommerce.services;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.serratec.ecommerce.domains.ItemPedido;
import br.com.serratec.ecommerce.domains.Pedido;
import br.com.serratec.ecommerce.dto.ItemPedidoRequestDTO;
import br.com.serratec.ecommerce.dto.ItemPedidoResponseDTO;
import br.com.serratec.ecommerce.dto.PedidoRequestDTO;
import br.com.serratec.ecommerce.dto.PedidoResponseDTO;
import br.com.serratec.ecommerce.exceptions.ResourceNotFoundException;
import br.com.serratec.ecommerce.repositories.ClienteRepository;
import br.com.serratec.ecommerce.repositories.ItemPedidoRepository;
import br.com.serratec.ecommerce.repositories.PedidoRepository;
import br.com.serratec.ecommerce.repositories.ProdutoRepository;
import br.com.serratec.ecommerce.utils.NullAwareBeanUtilsBean;

@Service
public class PedidoService {
    
    @Autowired
	private ItemPedidoRepository itemPedidoRepository;
	@Autowired
	private ClienteRepository clienteRepository;

    @Autowired
	private ProdutoRepository produtoRepository;
    
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

    public PedidoResponseDTO cadastrar(@Valid @RequestBody PedidoRequestDTO pedidoDTO) {

        var pedidoModel = mapper.map(pedidoDTO, Pedido.class);
        
        var cliente = clienteRepository.findById(pedidoDTO.getId_cliente()).get();

        pedidoModel.setCliente(cliente);
    
        List<ItemPedidoResponseDTO>  itensResponse = new ArrayList<ItemPedidoResponseDTO>();
    
        
        for(ItemPedidoRequestDTO itemPedido : pedidoDTO.getItemPedido()){
            
            var itemPedidoModel = mapper.map(itemPedido, ItemPedido.class);
            
        //setta Produto dentro de ItemPedido
            var produto = produtoRepository.findById(itemPedido.getId_produto()).get();
            
            itemPedidoModel.setProdutos(produto);

        //setta Pedido dentro de ItemPedido
            itemPedidoModel.setPedido(pedidoModel);
    
            calcularValores(itemPedidoModel);
    
            itemPedidoRepository.save(itemPedidoModel);
    
            itensResponse.add(mapper.map(itemPedidoModel, ItemPedidoResponseDTO.class));
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

    public void calcularValores (ItemPedido item){
        var qtd = item.getQuantidade();
        var desc = (item.getPercentual_desconto()/100);
        var prodValor = item.getProdutos().getValor_unitario();
    
        item.setValor_bruto(prodValor*qtd);
        item.setValor_liquido(item.getValor_bruto()-(item.getValor_bruto()*desc));
    }

}
