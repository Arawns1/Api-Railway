package com.residencia.ecommerce.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class RelatorioDTO {
	
	private Integer idPedido;
	private Date dataPedido;
	private BigDecimal valorTotal;
	private List<ItemPedidoResumidoDTO> listaItemPedidoResumidoDTO;
	public RelatorioDTO() {
		super();
	}
	public RelatorioDTO(Integer idPedido, Date dataPedido, BigDecimal valorTotal,
			List<ItemPedidoResumidoDTO> listaItemPedidoResumidoDTO) {
		super();
		this.idPedido = idPedido;
		this.dataPedido = dataPedido;
		this.valorTotal = valorTotal;
		this.listaItemPedidoResumidoDTO = listaItemPedidoResumidoDTO;
	}
	public Integer getIdPedido() {
		return idPedido;
	}
	public void setIdPedido(Integer idPedido) {
		this.idPedido = idPedido;
	}
	public Date getDataPedido() {
		return dataPedido;
	}
	public void setDataPedido(Date dataPedido) {
		this.dataPedido = dataPedido;
	}
	public BigDecimal getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}
	public List<ItemPedidoResumidoDTO> getListaItemPedidoResumidoDTO() {
		return listaItemPedidoResumidoDTO;
	}
	public void setListaItemPedidoResumidoDTO(List<ItemPedidoResumidoDTO> listaItemPedidoResumidoDTO) {
		this.listaItemPedidoResumidoDTO = listaItemPedidoResumidoDTO;
	}
	
	public String relatorio() {
		
		String produtos= "";
		
		for (ItemPedidoResumidoDTO item : this.listaItemPedidoResumidoDTO) {
			produtos = produtos+"Código: "+item.getIdProduto()
						+"\nNome: "+item.getNomeProduto()
						+"\nValor unitário: R$ "+item.getPrecoVenda()
						+"\nQuantidade: "+item.getQuantidade()
						+"\nValor Bruto: R$ "+item.getValorBruto()
						+"\nPercentual de desconto: "+item.getPercentualDesconto().multiply
						(new BigDecimal("100"))+" %"
						+"\nValor líquido: R$ "+item.getValorLiquido()+"\n\n";
		}
		
		return "\nRelatorio de Pedido\n"
				+ "\nNúmero do pedido: " + idPedido
				+ "\nData do Pedido: " + dataPedido
				+ "\nValor total: R$ " + valorTotal
				+ "\n\nProdutos: \n\n" + produtos;
	}	
	
	

}
