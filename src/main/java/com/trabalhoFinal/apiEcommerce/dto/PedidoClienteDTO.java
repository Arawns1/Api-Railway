package com.trabalhoFinal.apiEcommerce.dto;

import java.util.Date;

public class PedidoClienteDTO {

	private Double valor_total;
	private Date data_pedido;
	private String status;
	
	public Double getValor_total() {
		return valor_total;
	}
	public void setValor_total(Double valor_total) {
		this.valor_total = valor_total;
	}
	public Date getData_pedido() {
		return data_pedido;
	}
	public void setData_pedido(Date data_pedido) {
		this.data_pedido = data_pedido;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
