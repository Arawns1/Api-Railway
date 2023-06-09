package com.trabalhoFinal.apiEcommerce.dto;

import java.time.LocalDate;
import java.util.List;

public class PedidoDTO {

	private Integer id_pedido;
	private LocalDate data_pedido;
	private LocalDate data_entrega;
	private LocalDate data_envio;
	private String status;
	private Double valor_total;
	private Integer id_cliente;
	private List<ProdutoPedidoDTO> produtos;
	
	
	public Integer getId_pedido() {
		return id_pedido;
	}
	public void setId_pedido(Integer id_pedido) {
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Double getValor_total() {
		return valor_total;
	}
	public void setValor_total(Double valor_total) {
		this.valor_total = valor_total;
	}
	public Integer getId_cliente() {
		return id_cliente;
	}
	public void setId_cliente(Integer id_cliente) {
		this.id_cliente = id_cliente;
	}
	public List<ProdutoPedidoDTO> getProdutos() {
		return produtos;
	}
	public void setProdutos(List<ProdutoPedidoDTO> produtos) {
		this.produtos = produtos;
	}
	
	
}
