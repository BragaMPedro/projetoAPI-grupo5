package br.com.serratec.ecommerce.domains;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class ItemPedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_itemPedido")
	private Long id_itemPedido;
	
	@Column(name = "quantidade")
    @DecimalMin(value= "1")
	@NotNull(message = "Informe a quantidade do item! ")
	private int quantidade;
	
	@Column(name = "percentual_desconto")
	private double desconto;
	
    @Column(name = "valor_bruto")
	private double valorBruto;
    
    @Column(name = "valor_liquido")
	private double valorLiquido;
    
    @ManyToOne
    @JoinColumn(name = "id_produto")
    private Produto produto;

	@ManyToOne(cascade= CascadeType.PERSIST)
    @JoinColumn(name="id_pedido")
    @JsonBackReference
	private Pedido pedido;

    public Long getId_itemPedido() {
        return id_itemPedido;
    }

    public void setId_itemPedido(Long id_itemPedido) {
        this.id_itemPedido = id_itemPedido;
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

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

}