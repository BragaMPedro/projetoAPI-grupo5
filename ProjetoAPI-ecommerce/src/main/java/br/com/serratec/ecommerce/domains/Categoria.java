package br.com.serratec.ecommerce.domains;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

@Entity
public class Categoria {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_categoria;
	
	@Column(name = "nome_categoria", nullable = true)
	private @NotBlank String nome_categoria;
	
	private String descricao;
	
	// @OneToMany(mappedBy = "categoria", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	// Set<Produto> produtos;
	
	public Categoria() {
	}
	
	public Categoria(@NotBlank String nome_categoria, @NotBlank String descricao) {
		this.nome_categoria = nome_categoria;
		this.descricao = descricao;
	}

	public Long getId_categoria() {
		return id_categoria;
	}

	public void setId_categoria(Long id_categoria) {
		this.id_categoria = id_categoria;
	}

	public String getNome_categoria() {
		return this.nome_categoria;
	}

	public void setNome_categoria(String nome_categoria) {
		this.nome_categoria = nome_categoria;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	
	@Override
	public String toString() {
		return "Usuario {id categoria=" + id_categoria + ", nome categoria='" + nome_categoria + "', descricao='" + descricao + "'}";
	}

}
