package br.com.serratec.ecommerce.dto;

import java.time.LocalDate;
import java.util.List;

public class PedidoRequestDTO {

	private Long id_cliente;
private LocalDate data_pedido;
private Boolean status;
private List<ItemPedidoRequestDTO> itemPedido;
public Long getId_cliente() {
	return id_cliente;
}
public void setId_cliente(Long id_cliente) {
	this.id_cliente = id_cliente;
}
public LocalDate getData_pedido() {
	return data_pedido;
}
public void setData_pedido(LocalDate data_pedido) {
	this.data_pedido = data_pedido;
}
public Boolean getStatus() {
	return status;
}
public void setStatus(Boolean status) {
	this.status = status;
}
public List<ItemPedidoRequestDTO> getItemPedido() {
	return itemPedido;
}
public void setItemPedido(List<ItemPedidoRequestDTO> itemPedido) {
	this.itemPedido = itemPedido;
}

}