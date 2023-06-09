package com.trabalhoFinal.apiEcommerce.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.*;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id_itempedido", scope = ItemPedido.class)
@Entity
public class ItemPedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_itempedido")
	private Integer id_itempedido;

	@Column(name = "quantidade")
	private Integer quantidade;

	@Column(name = "preco_venda")
	private Double preco_venda;

	@NotNull(message = "O percentual de desconto deve ser preenchido!" )
	@Column(name = "percentual_desconto")
	private Double percentual_desconto;

	@Column(name = "valor_bruto")
	private Double valor_bruto;

	@Column(name = "valor_liquido")
	private Double valor_liquido;

	@ManyToOne
	@JoinColumn(name = "id_produto", referencedColumnName = "id_produto")
	private Produto produto;

	@ManyToOne
	@JoinColumn(name = "id_pedido", referencedColumnName = "id_pedido")
	private Pedido pedido;

	
	public Integer getId_itempedido() {
		return id_itempedido;
	}

	public void setId_itempedido(Integer id_itempedido) {
		this.id_itempedido = id_itempedido;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Double getPreco_venda() {
		return preco_venda;
	}

	public void setPreco_venda(Double preco_venda) {
		this.preco_venda = preco_venda;
	}

	public Double getPercentual_desconto() {
		return percentual_desconto;
	}

	public void setPercentual_desconto(Double percentual_desconto) {
		this.percentual_desconto = percentual_desconto;
	}

	public Double getValor_bruto() {
		return valor_bruto;
	}

	public void setValor_bruto(Double valor_bruto) {
		this.valor_bruto = valor_bruto;
	}

	public Double getValor_liquido() {
		return valor_liquido;
	}

	public void setValor_liquido(Double valor_liquido) {
		this.valor_liquido = valor_liquido;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

}
