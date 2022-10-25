package br.com.serratec.ecommerce.dto;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.serratec.ecommerce.domains.Cliente;

public class PedidoResponseDTO {

	private Long id_pedido;

	private LocalDate data_pedido;

	private LocalDate data_entrega;

	private LocalDate data_envio;

	private Boolean status;

	@JsonIgnore
	private Cliente cliente;

	private Long id_cliente = cliente.getId_cliente();

	@JsonBackReference
	private List<ItemPedidoResponseDTO> itemPedido;

	public Long getId_pedido() {
		return id_pedido;
	}

	public void setId_pedido(Long id_pedido) {
		this.id_pedido = id_pedido;
	}

	public LocalDate getData_pedido() {
		return data_pedido;
	}

	public void setData_pedido(LocalDate data_pedido) {
		this.data_pedido = data_pedido;
	}

	public LocalDate getData_entrega() {
		return data_entrega;
	}

	public void setData_entrega(LocalDate data_entrega) {
		this.data_entrega = data_entrega;
	}

	public LocalDate getData_envio() {
		return data_envio;
	}

	public void setData_envio(LocalDate data_envio) {
		this.data_envio = data_envio;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Long getId_cliente() {
		return id_cliente;
	}

	public void setId_cliente(Long id_cliente) {
		this.id_cliente = id_cliente;
	}

	public List<ItemPedidoResponseDTO> getItemPedido() {
		return itemPedido;
	}

	public void setItemPedido(List<ItemPedidoResponseDTO> itemPedido) {
		this.itemPedido = itemPedido;
	}


}