package br.com.serratec.ecommerce.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webtutsplus.ecommerce.model.Category;

import br.com.serratec.ecommerce.domains.Categoria;
import br.com.serratec.ecommerce.repositories.CategoriaRepository;
import br.com.serratec.gestaoserratec.exception.ResourceNotFoundException;

@Service
@Transactional
public class CategoriaService {

	private CategoriaRepository categoriarepository ;

	public CategoriaService(CategoriaRepository categoriarepository) {
		this.categoriarepository = categoriarepository;
	}
	
	public List<Categoria> listar_categorias(){
		return categoriarepository.findAll();
	}
	
	public void criar_categoria(Categoria categoria) {
		categoriarepository.save(categoria);
	}
	
	public Categoria leia_categoria(String nome_categoria) {
		return categoriarepository.findByNome_categoria(nome_categoria);
	}
	
	public Optional<Categoria> leia_categoria(Integer id_categoria) {
		return categoriarepository.findById(id_categoria);
	}
		
	
	public void atualizar_categoria(Integer id_categoria, Categoria nova_categoria) {
		Categoria categoria = categoriarepository.findById(id_categoria).get();
		categoria.setNome_categoria(nova_categoria.getNome_categoria());
		categoria.setDescricao(nova_categoria.getDescricao());
		//categoria.setProdutos(nova_categoria.getProdutos());
		
		categoriarepository.save(categoria);
	}
	
	public Optional<Categoria> obterPorId(Integer id_categoria) {
		
		Optional<Categoria> optCategoria = categoriarepository.findById(id_categoria);
		
		if(optCategoria.isEmpty()) {
			throw new ResourceNotFoundException("Não foi possivel encontrar a categoria com id " + id_categoria);
		}
		return optCategoria;
	}
	
	public void deletar_categoria(Integer id_categoria) {
		obterPorId(id_categoria);
		categoriarepository.deleteById(id_categoria);
	}

	private void validarModelo(Categoria categoria) {
		
		if(categoria.getDescricao() == null) {
			throw new ResourceBadRequestException("A categoria deve ter uma descrição!");
		}
	}
}