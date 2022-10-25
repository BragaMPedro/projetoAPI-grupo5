package br.com.serratec.ecommerce.dto;

public class ItemPedidoResponseDTO {

    private Long id_produto;
private int quantidade;
private double desconto;
public Long getId_produto() {
    return id_produto;
}
public void setId_produto(Long id_produto) {
    this.id_produto = id_produto;
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

}
