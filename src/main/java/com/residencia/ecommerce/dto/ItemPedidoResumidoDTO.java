package com.residencia.ecommerce.dto;

import java.math.BigDecimal;

import com.residencia.ecommerce.entities.ItemPedido;

public class ItemPedidoResumidoDTO {
	
	
	private Integer quantidade;
	
	private BigDecimal precoVenda;
	
	private BigDecimal percentualDesconto;
	
	private BigDecimal valorBruto;
	
	private BigDecimal valorLiquido;	
	
	private String nomeProduto;
	
	private Integer idProduto;	

	public ItemPedidoResumidoDTO() {
		super();	
	}
	public ItemPedidoResumidoDTO(ItemPedido itemPedido) {
		super();
		this.quantidade = itemPedido.getQuantidade();
		this.precoVenda = itemPedido.getPrecoVenda();
		this.percentualDesconto = itemPedido.getPercentualDesconto();
		this.valorBruto = itemPedido.getValorBruto();
		this.valorLiquido = itemPedido.getValorLiquido();
		this.nomeProduto = itemPedido.getProduto().getNome();
		this.idProduto = itemPedido.getProduto().getIdProduto();
	
	}
	public ItemPedidoResumidoDTO(Integer quantidade, BigDecimal precoVenda, BigDecimal percentualDesconto,
			BigDecimal valorBruto, BigDecimal valorLiquido, String nomeProduto, Integer idProduto) {
		super();
		this.quantidade = quantidade;
		this.precoVenda = precoVenda;
		this.percentualDesconto = percentualDesconto;
		this.valorBruto = valorBruto;
		this.valorLiquido = valorLiquido;
		this.nomeProduto = nomeProduto;
		this.idProduto = idProduto;
	}
	public Integer getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	public BigDecimal getPrecoVenda() {
		return precoVenda;
	}
	public void setPrecoVenda(BigDecimal precoVenda) {
		this.precoVenda = precoVenda;
	}
	public BigDecimal getPercentualDesconto() {
		return percentualDesconto;
	}
	public void setPercentualDesconto(BigDecimal percentualDesconto) {
		this.percentualDesconto = percentualDesconto;
	}
	public BigDecimal getValorBruto() {
		return valorBruto;
	}
	public void setValorBruto(BigDecimal valorBruto) {
		this.valorBruto = valorBruto;
	}
	public BigDecimal getValorLiquido() {
		return valorLiquido;
	}
	public void setValorLiquido(BigDecimal valorLiquido) {
		this.valorLiquido = valorLiquido;
	}
	public String getNomeProduto() {
		return nomeProduto;
	}
	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}
	public Integer getIdProduto() {
		return idProduto;
	}
	public void setIdProduto(Integer idProduto) {
		this.idProduto = idProduto;
	}		
}
