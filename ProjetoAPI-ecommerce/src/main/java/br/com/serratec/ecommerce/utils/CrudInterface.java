package br.com.serratec.ecommerce.utils;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface CrudInterface<T> {

    @GetMapping
	public ResponseEntity<List<T>> getAll();
	
	@GetMapping("/{id}")
	public ResponseEntity<T> getById(@PathVariable Long Id);
	
	@PostMapping
	public ResponseEntity<T> post(@Valid @RequestBody T objeto);
	
	@PutMapping("/{id}")
	public ResponseEntity<T> put(@PathVariable Long id, @Valid @RequestBody T objeto);

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletar(@PathVariable Long id);

    @PatchMapping("/{id}")
	public ResponseEntity<T> patch(@PathVariable Long id, @Valid @RequestBody T objeto) throws IllegalAccessException, InvocationTargetException;  
}
