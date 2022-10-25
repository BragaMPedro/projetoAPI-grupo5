package br.com.serratec.ecommerce.dto;

import java.util.List;

public class CategoriaRequestDTO {
	
	private String nome_categoria;
	
	private String descricao;
	
	private List<ProdutoRequestDTO> produtos;
	
	public String getNome_categoria() {
		return nome_categoria;
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
	public List<ProdutoRequestDTO> getProdutos() {
		return produtos;
	}
	public void setProdutos(List<ProdutoRequestDTO> produtos) {
		this.produtos = produtos;
	}
}
