package br.com.serratec.ecommerce.services;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.serratec.ecommerce.domains.Categoria;
import br.com.serratec.ecommerce.exceptions.ResourceBadRequestException;
import br.com.serratec.ecommerce.exceptions.ResourceNotFoundException;
import br.com.serratec.ecommerce.repositories.CategoriaRepository;
import br.com.serratec.ecommerce.utils.NullAwareBeanUtilsBean;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
	private NullAwareBeanUtilsBean beanUtilsBean;

    public List<Categoria> obterTodos() {
       
        List<Categoria> lista = categoriaRepository.findAll();
		return lista;
    }

    public Optional<Categoria> obterById(Long id) {
        var optCategoria = categoriaRepository.findById(id);

		if(optCategoria.isEmpty()){
			throw new ResourceNotFoundException("Não foi possível encontrar a Categoria id " + id);
		}

		return optCategoria;
    }

    public Categoria cadastrar(Categoria categoria) {

        List<Categoria> lista = categoriaRepository.findAll();
        
        for (int i = 0; i < lista.size(); i++) {
            if(categoria.getNome_categoria() == lista.get(i).getNome_categoria()){
               throw new ResourceBadRequestException("Categoria já existente");
            }
        }
    
        return categoriaRepository.save(categoria);
    }

    public Categoria atualizar(Long id, Categoria categoria) {
        
        //validação rápida
        obterById(id);
       
        //realiza o método em si
		categoria.setId_categoria(id);
		categoriaRepository.save(categoria);

		return categoria;
    }

    public void deletar(Long id) {
        
        obterById(id);
		categoriaRepository.deleteById(id);
    }

    public Categoria atualizarParcial(Long id, Categoria categoria)
                throws IllegalAccessException, InvocationTargetException {
       
        //Objeto do banco de dados
        var categoriaDB = obterById(id);
        
        //realiza método em si
        beanUtilsBean.copyProperties(categoriaDB, categoria);

		categoriaRepository.save(categoriaDB.get());
		
        //converte Entidade em DTO e retorna
        return categoriaDB.get();
    }
    
}
