package com.residencia.ecommerce.dto;

import java.math.BigDecimal;

import com.residencia.ecommerce.entities.ItemPedido;
import com.residencia.ecommerce.entities.Pedido;
import com.residencia.ecommerce.entities.Produto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ItemPedidoDTO {
	
	private Integer idItemPedido;
	@NotNull
	@Min (1)
	private Integer quantidade;
	
	private BigDecimal precoVenda;
	@DecimalMin(value = "0.0")
	@DecimalMax(value = "1.0")
	private BigDecimal percentualDesconto;
	
	private BigDecimal valorBruto;
	
	private BigDecimal valorLiquido;
	@NotNull
	private Pedido pedido;
	@NotNull
	private Produto produto;
	@NotNull
	@NotBlank
	String status;
	
	public ItemPedidoDTO(ItemPedido itemPedido) {
		super();
		this.idItemPedido = itemPedido.getIdItemPedido();
		this.quantidade = itemPedido.getQuantidade();
		this.precoVenda = itemPedido.getPrecoVenda();
		this.percentualDesconto = itemPedido.getPercentualDesconto();
		this.valorBruto = itemPedido.getValorBruto();
		this.valorLiquido = itemPedido.getValorLiquido();
		this.pedido = itemPedido.getPedido();
		this.produto = itemPedido.getProduto();
		this.status = itemPedido.getStatus();
	}

	public ItemPedidoDTO() {
		super();
	}

	public ItemPedidoDTO(Integer idItemPedido, @NotNull @Min(1) Integer quantidade, BigDecimal precoVenda,
			@DecimalMin("0.0") @DecimalMax("1.0") BigDecimal percentualDesconto, BigDecimal valorBruto,
			BigDecimal valorLiquido, @NotNull Pedido pedido, @NotNull Produto produto,
			@NotNull @NotBlank String status) {
		super();
		this.idItemPedido = idItemPedido;
		this.quantidade = quantidade;
		this.precoVenda = precoVenda;
		this.percentualDesconto = percentualDesconto;
		this.valorBruto = valorBruto;
		this.valorLiquido = valorLiquido;
		this.pedido = pedido;
		this.produto = produto;
		this.status = status;
	}

	public Integer getIdItemPedido() {
		return idItemPedido;
	}

	public void setIdItemPedido(Integer idItemPedido) {
		this.idItemPedido = idItemPedido;
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

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}	
}
