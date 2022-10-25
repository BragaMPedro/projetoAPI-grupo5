package br.com.serratec.ecommerce.dto;

import br.com.serratec.ecommerce.domains.Produto;

public class ItemPedidoRequestDTO {
    
    private Produto ProdutoRequestDTO;

    private int quantidade;

    // private double preco_venda;

    private double percentual_desconto;

    public Produto getProdutoRequestDTO() {
        return ProdutoRequestDTO;
    }

    public void setProdutoRequestDTO(Produto produtoRequestDTO) {
        ProdutoRequestDTO = produtoRequestDTO;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getPercentual_desconto() {
        return percentual_desconto;
    }

    public void setPercentual_desconto(double percentual_desconto) {
        this.percentual_desconto = percentual_desconto;
    }
    
}
