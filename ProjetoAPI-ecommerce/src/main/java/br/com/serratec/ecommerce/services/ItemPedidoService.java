package br.com.serratec.ecommerce.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.serratec.ecommerce.domains.ItemPedido;
import br.com.serratec.ecommerce.dto.ItemPedidoRequestDTO;
import br.com.serratec.ecommerce.dto.ItemPedidoResponseDTO;
import br.com.serratec.ecommerce.exceptions.ResourceNotFoundException;
import br.com.serratec.ecommerce.repositories.ItemPedidoRepository;

@Service
public class ItemPedidoService {
	
	@Autowired
	private ItemPedidoRepository repositorio;
	
	private ModelMapper mapper = new ModelMapper();
	
	public List<ItemPedidoResponseDTO> obterTodos() {
		List<ItemPedido> lista = repositorio.findAll();

		return lista.stream()
				.map(itemPedido -> mapper.map(itemPedido, ItemPedidoResponseDTO.class))
				.collect(Collectors.toList());
	}
	
	public Optional<ItemPedidoResponseDTO> obterPorId(Long id_itemPedido){
		
		var optItemPedido = repositorio.findById(id_itemPedido);
		
		if(optItemPedido.isEmpty()) {
			throw new ResourceNotFoundException("Não foi possivel encontrar o itemPedido com id " + id_itemPedido);
		}
		
		ItemPedidoResponseDTO dto = mapper.map(optItemPedido.get(), ItemPedidoResponseDTO.class);
		
		return Optional.of(dto);
	}
	
	public ItemPedidoResponseDTO cadastrar(ItemPedidoRequestDTO itemPedido ) {
		
		var itemPedidoModel = mapper.map(itemPedido, ItemPedido.class);

		repositorio.save(itemPedidoModel);
        
        var response = mapper.map(itemPedidoModel, ItemPedidoResponseDTO.class);
        return response;
	}
	
	public ItemPedidoResponseDTO atualizar(Long id_itemPedido, ItemPedidoRequestDTO itemPedido) {
		
		obterPorId(id_itemPedido);
		
		var itemPedidoModel = mapper.map(itemPedido, ItemPedido.class);
		
		itemPedidoModel.setId_itemPedido(id_itemPedido);
		repositorio.save(itemPedidoModel);
		
		return mapper.map(itemPedidoModel, ItemPedidoResponseDTO.class);
		
	}
	
	public void deletar(Long id) {
		obterPorId(id);
		repositorio.deleteById(id);
	}
}