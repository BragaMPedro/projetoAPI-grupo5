package br.com.serratec.ecommerce.dto;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import br.com.serratec.ecommerce.dto.enderecoDTOs.EnderecoRequestDTO;

public class ClienteRequestDTO {

	private String nome_completo; 
	
	private String email;
	
	private String cpf;
	
	private String telefone;
	
	@JsonFormat(shape = Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate data_nascimento;

	private List<EnderecoRequestDTO> enderecos;

	public List<EnderecoRequestDTO> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<EnderecoRequestDTO> enderecos) {
		this.enderecos = enderecos;
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
	
	
}
