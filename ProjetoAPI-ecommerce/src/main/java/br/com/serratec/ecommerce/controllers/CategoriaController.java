package br.com.serratec.ecommerce.controllers;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.serratec.ecommerce.domains.Categoria;
import br.com.serratec.ecommerce.services.CategoriaService;
import br.com.serratec.ecommerce.utils.CrudInterface;

@RestController
@RequestMapping("/categorias")
public class CategoriaController implements CrudInterface<Categoria>{

    @Autowired
    private CategoriaService categoriaService;

    //GetMapping
    @Override
    public ResponseEntity<List<Categoria>> getAll() {
        
        return ResponseEntity.ok(categoriaService.obterTodos());
    }

    //GetMapping(/ID)
    @Override
    public ResponseEntity<Categoria> getById(Long Id) {
        
        var categoria = categoriaService.obterById(Id);
        return ResponseEntity.ok(categoria.get());
    }

    //PosttMapping
    @Override
    public ResponseEntity<Categoria> post(@Valid Categoria categoria) {
       
        var cat = categoriaService.cadastrar(categoria);
		return new ResponseEntity<>(cat, HttpStatus.CREATED);
    }

    //PutMapping(/ID)
    @Override
    public ResponseEntity<Categoria> put(Long id, @Valid Categoria categoria) {
        
        var cat = categoriaService.atualizar(id, categoria);
		return new ResponseEntity<>(cat, HttpStatus.CREATED);
    }

    //DeleteMapping(/ID)
    @Override
    public ResponseEntity<?> deletar(Long id) {
        
        categoriaService.deletar(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //PatchMapping(/ID)
    @Override
    public ResponseEntity<Categoria> patch(Long id, @Valid Categoria categoria)
            throws IllegalAccessException, InvocationTargetException {
      
        var cat = categoriaService.atualizarParcial(id, categoria);
		return new ResponseEntity<>(cat, HttpStatus.PARTIAL_CONTENT);
    }
    
}
