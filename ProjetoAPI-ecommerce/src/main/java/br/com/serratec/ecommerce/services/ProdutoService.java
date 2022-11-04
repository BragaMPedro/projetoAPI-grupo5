package br.com.serratec.ecommerce.services;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.serratec.ecommerce.domains.Produto;
import br.com.serratec.ecommerce.dto.ProdutoRequestDTO;
import br.com.serratec.ecommerce.dto.ProdutoResponseDTO;
import br.com.serratec.ecommerce.exceptions.ResourceNotFoundException;
import br.com.serratec.ecommerce.repositories.ProdutoRepository;
import br.com.serratec.ecommerce.utils.NullAwareBeanUtilsBean;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository repositorio;

	@Autowired
	private NullAwareBeanUtilsBean beanUtilsBean;

	private ModelMapper mapper = new ModelMapper();

	public List<ProdutoResponseDTO> obterTodos() {
		List<Produto> lista = repositorio.findAll();

		return lista.stream()
				.map(produto -> mapper.map(produto, ProdutoResponseDTO.class))
				.collect(Collectors.toList());
	}

	public Optional<ProdutoResponseDTO> obterPorId(Long id_produto) {

		var optProduto = repositorio.findById(id_produto);

		if (optProduto.isEmpty()) {
			throw new ResourceNotFoundException("Não foi possivel encontrar o produto com id " + id_produto);
		}

		ProdutoResponseDTO dto = mapper.map(optProduto.get(), ProdutoResponseDTO.class);

		return Optional.of(dto);
	}

	public ProdutoResponseDTO cadastrar(ProdutoRequestDTO produto) {

		var produtoModel = mapper.map(produto, Produto.class);

		repositorio.save(produtoModel);

		var response = mapper.map(produtoModel, ProdutoResponseDTO.class);
		return response;
	}

	public ProdutoResponseDTO atualizar(Long id_produto, ProdutoRequestDTO produto) {

		obterPorId(id_produto);

		var produtoModel = mapper.map(produto, Produto.class);

		produtoModel.setId_produto(id_produto);
		repositorio.save(produtoModel);

		return mapper.map(produtoModel, ProdutoResponseDTO.class);

	}

	public void deletar(Long id) {
		obterPorId(id);
		repositorio.deleteById(id);
	}

	public ProdutoResponseDTO atualizarParcial(Long id, ProdutoRequestDTO produto)   {
       
        //Objeto do banco de dados
        var produtoDB = obterPorId(id);
		
		//Conversão do DTOrequest para Entidade
		var produtoModel = mapper.map(produto, Produto.class);
		var produtoDBmodel = mapper.map(produtoDB.get(), Produto.class);
        
        //realiza método em si
        try {
			beanUtilsBean.copyProperties(produtoDBmodel, produtoModel);
        } catch (IllegalAccessException e) {
			
			e.printStackTrace();
        } catch (InvocationTargetException e) {
			
			e.printStackTrace();
        }

		repositorio.save(produtoDBmodel);
		
        //converte Entidade em DTO e retorna
        return mapper.map(produtoDBmodel, ProdutoResponseDTO.class);
    }
}
