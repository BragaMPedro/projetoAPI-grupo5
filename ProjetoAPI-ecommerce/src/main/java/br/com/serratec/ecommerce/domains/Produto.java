package br.com.serratec.ecommerce.domains;


import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Produto {
	
	
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name="id_produto")
		private Long id_produto;
		
		@Column(name="nome",nullable=false, length=40 )
		@NotBlank(message = "Preencha o nome!")
		private String nome;
		
		@Column(name="descricao", nullable=false, length=70)
		private String descricao;
		
		@DecimalMin(value= "1")
		@Column(name="qtd_estoque")
		private int qtd_estoque;
		
		@Column(name="data_cadastro")
	    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	    private LocalDate data_cadastro;
		
		@DecimalMin(value="10")
		@NotBlank(message = "Preencha o valor!")
		@Column(name="valor_unitario", nullable=false)
		private Double valor_unitario;
		
		@Column(name="imagem", columnDefinition="NVARCHAR(MAX)")
		private String imagem;
		
		@ManyToOne
		@JoinColumn(name="id_categoria")
		private Categoria categoria;
		
//		@ManyToOne
//		@JoinColumn(name="id_item_pedido")
//		private ItemPedido itemPedido;
		

		public Long getId_produto() {
			return id_produto;
		}

		public void setId_produto(Long id_produto) {
			this.id_produto = id_produto;
		}

		public String getNome() {
			return nome;
		}

		public void setNome(String nome) {
			this.nome = nome;
		}

		public String getDescricao() {
			return descricao;
		}

		public void setDescricao(String descricao) {
			this.descricao = descricao;
		}

		public int getQtd_estoque() {
			return qtd_estoque;
		}

		public void setQtd_estoque(int qtd_estoque) {
			this.qtd_estoque = qtd_estoque;
		}

		public LocalDate getData_cadastro() {
			return data_cadastro;
		}

		public void setData_cadastro(LocalDate data_cadastro) {
			this.data_cadastro = data_cadastro;
		}

		public Double getValor_unitario() {
			return valor_unitario;
		}

		public void setValor_unitario(Double valor_unitario) {
			this.valor_unitario = valor_unitario;
		}

		public String getImagem() {
			return imagem;
		}

		public void setImagem(String imagem) {
			this.imagem = imagem;
		}

		public Categoria getCategoria() {
			return categoria;
		}

		public void setCategoria(Categoria categoria) {
			this.categoria = categoria;
		}
		
}
