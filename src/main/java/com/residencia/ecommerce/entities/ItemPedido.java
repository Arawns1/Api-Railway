package com.residencia.ecommerce.entities;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;

@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "idItemPedido"
        )
@Entity
@Table(name = "item_pedido")
public class ItemPedido {
	
		
	@Id // indica que esse atributo é chave primaria (obrigatorio)
	@GeneratedValue(strategy = GenerationType.IDENTITY) // indica se o java ou o banco de dados será responsavel pelo autoincremento
	@Column(name = "id_item_pedido")
	private Integer idItemPedido;
	
	@Min (1)	
	@Column ( name = "quantidade")
	private Integer quantidade;
	
	
	@DecimalMin(value = "0.0")    
	@Column ( name = "preco_venda")
	private BigDecimal precoVenda;
	
	@DecimalMin(value = "0.0")
	@DecimalMax(value = "1.0")
	@Column ( name = "percentual_desconto")
	private BigDecimal percentualDesconto;
	
	@Column ( name = "valor_bruto")
	private BigDecimal valorBruto;
	
	
	@Column ( name = "valor_liquido")
	private BigDecimal valorLiquido; 
	
	
	@ManyToOne 
	@JoinColumn(name = "id_pedido", 
					referencedColumnName = "id_pedido")
	private Pedido pedido;
	
	@ManyToOne 
	@JoinColumn(name = "id_produto", 
					referencedColumnName = "id_produto")
	private Produto produto;
	
	private String status;
	
	

	public ItemPedido() {	
	}
	
	public ItemPedido(@Min(1) Integer quantidade, @DecimalMin("0.0") @DecimalMax("1.0") BigDecimal percentualDesconto,
			Pedido pedido, Produto produto, String status) {
		super();
		this.quantidade = quantidade;
		this.percentualDesconto = percentualDesconto;
		this.pedido = pedido;
		this.produto = produto;
		this.status = status;		
		this.precoVenda = produto.getValorUnitario();
		this.valorBruto = this.precoVenda.multiply(BigDecimal.valueOf(quantidade));
		this.valorLiquido = this.valorBruto.subtract(this.valorBruto.multiply(percentualDesconto));
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
