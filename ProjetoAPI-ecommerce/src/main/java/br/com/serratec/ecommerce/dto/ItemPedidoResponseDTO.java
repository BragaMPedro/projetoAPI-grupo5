package br.com.serratec.ecommerce.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;

public class ItemPedidoResponseDTO {

    private Long id_itemPedido;
    private int quantidade;
    private double desconto;
    private double valorBruto;
    private double valorLiquido;
    private ProdutoResponseDTO produto;

    @JsonBackReference
    private PedidoResponseDTO pedido;

    public ProdutoResponseDTO getProduto() {
        return produto;
    }
    public void setProduto(ProdutoResponseDTO produto) {
        this.produto = produto;
    }
    public PedidoResponseDTO getPedido() {
        return pedido;
    }
    public void setPedido(PedidoResponseDTO pedido) {
        this.pedido = pedido;
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
    public Long getId_itemPedido() {
        return id_itemPedido;
    }
    public void setId_itemPedido(Long id_itemPedido) {
        this.id_itemPedido = id_itemPedido;
    }
}
