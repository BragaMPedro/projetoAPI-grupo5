package br.com.serratec.ecommerce.domains;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name= "cliente")
public class Cliente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_cliente;
	
	@Email
	@NotBlank (message = "Preencha o e-mail")
	@Column(name = "email", length = 50, unique = true)
	private String email;
	
	@NotBlank (message = "Preencha o nome completo")
	@Column(name = "nome_completo", length = 50)
	private String nome_completo;
	
	@Pattern(regexp= "([0-9]{11})$")
	@NotBlank (message = "Preencha o CPF")
	@Column(name = "cpf", length = 11, unique = true)
	private String cpf;
	
	@Column(name = "telefone", length = 11)
	private String telefone;
	
	@Column(name = "data_nascimento")
	@Past(message = "É um robô viajante do tempo você? Como é que nasceu no futuro?")
	private LocalDate data_nascimento;

	@OneToMany(mappedBy = "cliente")
	@JsonBackReference
	private List<Endereco> enderecos;

	@OneToMany(mappedBy = "cliente")
	@JsonBackReference
	private List<Pedido> pedidos;

	public Long getId_cliente() {
		return id_cliente;
	}

	public void setId_cliente(Long id_cliente) {
		this.id_cliente = id_cliente;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNome_completo() {
		return nome_completo;
	}

	public void setNome_completo(String nome_completo) {
		this.nome_completo = nome_completo;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public LocalDate getData_nascimento() {
		return data_nascimento;
	}

	public void setData_nascimento(LocalDate data_nascimento) {
		this.data_nascimento = data_nascimento;
	}

	public List<Endereco> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

}


