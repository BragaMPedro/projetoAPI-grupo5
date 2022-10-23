package br.com.serratec.ecommerce.domains;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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

    @ManyToOne
	@JoinColumn(name="id_pedido")
	private Pedido pedido;

    @OneToOne
	@JoinColumn(name="id_produto")
	private Produto produto;

}