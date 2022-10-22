package br.com.serratec.ecommerce.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.serratec.ecommerce.domains.Produto;
import br.com.serratec.ecommerce.exceptions.ResourceNotFoundException;
import br.com.serratec.ecommerce.repositories.ProdutoRepository;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository repositorio;
	
	public List<Produto> obterTodos() {
		return repositorio.findAll();
	}
	
	public Optional<Produto> obterPorId(Long id_produto){
		
		Optional<Produto> optProduto = repositorio.findById(id_produto);
		
		if(optProduto.isEmpty()) {
			throw new ResourceNotFoundException("Não foi possivel encontrar o produto com id " + id_produto);
		}
		
		return optProduto;
	}
	
	public Produto cadastrar(Produto produto) {
		
		produto.setId_produto(null);
		return repositorio.save(produto);
	}
	
	public Produto atualizar(Long id_produto, Produto produto) {
		
		obterPorId(id_produto);
		
		produto.setId_produto(id_produto);
		return repositorio.save(produto);
	}
	
	public void deletar(Long id) {
		obterPorId(id);
		repositorio.deleteById(id);
	}
}
