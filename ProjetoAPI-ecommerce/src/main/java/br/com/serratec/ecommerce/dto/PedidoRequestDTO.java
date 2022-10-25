package br.com.serratec.ecommerce.dto;

import java.time.LocalDate;
import java.util.List;

public class PedidoRequestDTO {
	
	private LocalDate data_pedido;
	
	// private LocalDate data_entrega; 
	
	// private LocalDate data_envio;
	
	// private Boolean status;

	private List<ItemPedidoRequestDTO> itens;

	public List<ItemPedidoRequestDTO> getItens() {
		return itens;
	}

	public void setItens(List<ItemPedidoRequestDTO> itens) {
		this.itens = itens;
	}

	public LocalDate getData_pedido() {
		return data_pedido;
	}

	public void setData_pedido(LocalDate data_pedido) {
		this.data_pedido = data_pedido;
	}
	
}
