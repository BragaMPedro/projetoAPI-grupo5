package br.com.serratec.ecommerce.domains;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;

@Entity
public class ItemPedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_itemPedido")
	private Long id_itemPedido;
	
	@Column(name = "quantidade")
    @DecimalMin(value= "1")
	@NotBlank(message = "Informe a quantidade do item! ")
	private int quantidade;
	
	@Column(name = "preco_venda")
    @NotBlank(message = "Informe o preço por unidade! ")
	private double preco_venda;
	
	@Column(name = "percentual_desconto")
	private double percentual_desconto;
	
    @Column(name = "valor_bruto")
	private double valor_bruto;
    
	
    @Column(name = "valor_liquido")
	private double valor_liquido;

    @OneToMany
	@JoinColumn(name="id_pedido")
	private Pedido pedido;

    @OneToMany
	@JoinColumn(name="id_produto")
	private Produto produto;

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

    public double getPreco_venda() {
        return preco_venda;
    }

    public void setPreco_venda(double preco_venda) {
        this.preco_venda = preco_venda;
    }

    public double getPercentual_desconto() {
        return percentual_desconto;
    }

    public void setPercentual_desconto(double percentual_desconto) {
        this.percentual_desconto = percentual_desconto;
    }

    public double getValor_bruto() {
        return valor_bruto;
    }

    public void setValor_bruto(double valor_bruto) {
        this.valor_bruto = valor_bruto;
    }

    public double getValor_liquido() {
        return valor_liquido;
    }

    public void setValor_liquido(double valor_liquido) {
        this.valor_liquido = valor_liquido;
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