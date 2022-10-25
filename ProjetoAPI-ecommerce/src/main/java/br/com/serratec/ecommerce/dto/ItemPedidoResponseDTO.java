package br.com.serratec.ecommerce.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.serratec.ecommerce.domains.Pedido;
import br.com.serratec.ecommerce.domains.Produto;

public class ItemPedidoResponseDTO {

    private Long id;
    @JsonIgnore
    private Produto produto;
    private Long id_produto = produto.getId_produto();
    @JsonIgnore
    private Pedido pedido;
    private Long id_pedido = pedido.getId_pedido();
    private int quantidade;
    private double desconto;
    private double valorBruto;
    private double valorLiquido;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Produto getProduto() {
        return produto;
    }
    public void setProduto(Produto produto) {
        this.produto = produto;
    }
    public Long getId_produto() {
        return id_produto;
    }
    public void setId_produto(Long id_produto) {
        this.id_produto = id_produto;
    }
    public Pedido getPedido() {
        return pedido;
    }
    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }
    public Long getId_pedido() {
        return id_pedido;
    }
    public void setId_pedido(Long id_pedido) {
        this.id_pedido = id_pedido;
    }
    public int getQuantidade() {
        return quantidade;
    }
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    public double getDesconto() {
        return desconto;
    }
    public void setDesconto(double desconto) {
        this.desconto = desconto;
    }
    public double getValorBruto() {
        return valorBruto;
    }
    public void setValorBruto(double valorBruto) {
        this.valorBruto = valorBruto;
    }
    public double getValorLiquido() {
        return valorLiquido;
    }
    public void setValorLiquido(double valorLiquido) {
        this.valorLiquido = valorLiquido;
    }

}
