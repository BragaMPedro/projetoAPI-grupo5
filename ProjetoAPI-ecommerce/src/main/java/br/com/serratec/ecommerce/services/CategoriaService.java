package br.com.serratec.ecommerce.services;

import java.security.PrivateKey;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.serratec.ecommerce.domains.Categoria;
import br.com.serratec.ecommerce.repositories.CategoriaRepository;

@Service
@Transactional
public class CategoriaService {

	
	@Autowired
	private CategoriaRepository categoriarepository;

	public List<Categoria> obterTodos() {
		return categoriarepository.findAll();
	}
	
	public Optional<Categoria> obterPorid_categoria(Integer id_categoria){
		
		Optional<Categoria> optCategoria = categoriarepository.findById(id_categoria);
		
		if(optCategoria.isEmpty()) {
			throw new ResourceNotFoundException("Não foi possivel encontrar a categoria com id_categoria " + id_categoria);
		}
		
		return optCategoria;
	}
	
	public Categoria cadastrar_categoria(Categoria categoria) {
		validarModelo(categoria);
		
		categoria.setId(null);
		return categoriarepository.save(categoria);
	}
	
	public Categoria atualizar_categoria (Integer id_categoria, Categoria categoria) {
		
		obterPorid_categoria(id_categoria);
		
		validarModelo(categoria);
		
		categoria.setId(id_categoria);
		return categoriarepository.save(categoria);
	}
	
	public void deletar_categoria(Integer id_categoria) {
		obterPorid_categoria(id_categoria);
		categoriarepository.deleteById(id_categoria);
	}
	
	private void validarModelo(Categoria categoria) {
		
		if(categoria.getDescricao() == null) {
			throw new ResourceBadRequestException("A categoria deve ter uma descrição!");
		}
	}
}