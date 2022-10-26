package br.com.serratec.ecommerce.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.serratec.ecommerce.domains.ItemPedido;
import br.com.serratec.ecommerce.domains.Produto;
import br.com.serratec.ecommerce.dto.ItemPedidoRequestDTO;
import br.com.serratec.ecommerce.dto.ItemPedidoResponseDTO;
import br.com.serratec.ecommerce.exceptions.ResourceBadRequestException;
import br.com.serratec.ecommerce.exceptions.ResourceNotFoundException;
import br.com.serratec.ecommerce.repositories.ItemPedidoRepository;
import br.com.serratec.ecommerce.repositories.ProdutoRepository;

@Service
public class ItemPedidoService {
	
	@Autowired
	private ItemPedidoRepository repositorio;
	@Autowired
	private ProdutoRepository produtoRepository;
	
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
		
		//pegando e settando Produto
		var optProduto = itemPedido.getId_produto();
		var produto = produtoRepository.findById(optProduto).get();

		if(validarQtd(itemPedidoModel, produto) == false){
			throw new ResourceBadRequestException("Quantidade do pedido maior que estoque disponível!");
		}

		itemPedidoModel.setProduto(produto);
		
		//Calculando atributos
		calcularValores(itemPedidoModel);

		//salvando Banco de dados
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

	public void calcularValores (ItemPedido item){
        var qtd = item.getQuantidade();
        var desc = (item.getDesconto()/100);
        var prodValor = item.getProduto().getValor_unitario();
    
        item.setValorBruto(prodValor*qtd);
        item.setValorLiquido(item.getValorBruto() - (item.getValorBruto()*desc));
    }
	
	public Boolean validarQtd (ItemPedido itemPedidoQtd, Produto produtoQtd){

		if(itemPedidoQtd.getQuantidade() > produtoQtd.getQtd_estoque()){
			return false;
		}
		else{
			return true;
		}

	}

}