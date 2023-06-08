package com.residencia.ecommerce.services;

import java.math.BigDecimal;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.residencia.ecommerce.dto.ItemPedidoDTO;
import com.residencia.ecommerce.dto.ItemPedidoResumidoDTO;
import com.residencia.ecommerce.dto.RelatorioDTO;
import com.residencia.ecommerce.entities.ItemPedido;
import com.residencia.ecommerce.entities.Pedido;
import com.residencia.ecommerce.entities.Produto;
import com.residencia.ecommerce.exceptions.CustomException;
import com.residencia.ecommerce.repositories.ItemPedidoRepository;
import com.residencia.ecommerce.repositories.PedidoRepository;
import com.residencia.ecommerce.repositories.ProdutoRepository;

@Service
public class ItemPedidoService {
	
	@Autowired
	ItemPedidoRepository itemPedidoRepository;
	@Autowired
	ProdutoRepository produtoRepository;
	@Autowired
	PedidoRepository pedidoRepository;	
	@Autowired
	ModelMapper modelMapper;
	@Autowired
	EmailService emailService;
	
	public List<ItemPedidoDTO> getAllItemPedidosDTO() {
		List<ItemPedido> listaItemPedido = itemPedidoRepository.findAll();		
		List<ItemPedidoDTO> listaItemPedidoDTO = listaItemPedido.stream().map(x -> new ItemPedidoDTO(x)).toList();						
		return listaItemPedidoDTO;
	}
	
	public ItemPedidoDTO getItemPedidoDtoById(Integer id) {
		ItemPedido itemPedido = itemPedidoRepository.findById(id).orElse(null);
		
		if(itemPedido==null)
			return null;
		
		ItemPedidoDTO itemPedidoDTO = modelMapper.map(itemPedido, ItemPedidoDTO.class);
		
		return itemPedidoDTO;		
	}
	
	public ItemPedidoDTO saveItemPedidoDTO(ItemPedidoDTO itemPedidoDTO) {
		
		Integer quantidade = itemPedidoDTO.getQuantidade();
		Produto produto = produtoRepository.findById(itemPedidoDTO.getProduto().getIdProduto()).orElse(null);
		Pedido pedido = pedidoRepository.findById(itemPedidoDTO.getPedido().getIdPedido()).orElse(null);
		if(produto == null) {
			throw new CustomException("Produto de id: "+itemPedidoDTO.getProduto().getIdProduto()+ " não encontrado");
		}
		if(pedido == null) {
			throw new CustomException("Pedido de id: "+itemPedidoDTO.getPedido().getIdPedido()+ " não encontrado");
		}
		if(!pedido.getStatus().toLowerCase().equals("aberto")) {
			throw new CustomException("Pedido de id: "+itemPedidoDTO.getPedido().getIdPedido()+ " Não tem o status 'aberto'");
		}
		
		if (produto.getQtdEstoque() >= quantidade) {			
			itemPedidoDTO.setPedido(pedido);
			itemPedidoDTO.setProduto(produto);
			if(null == itemPedidoDTO.getPrecoVenda()) {
				itemPedidoDTO.setPrecoVenda(produto.getValorUnitario());
			}			
			itemPedidoDTO.setValorBruto(itemPedidoDTO.getPrecoVenda().multiply(BigDecimal.valueOf(quantidade)));
			itemPedidoDTO.setValorLiquido(itemPedidoDTO.getValorBruto().subtract(itemPedidoDTO.getValorBruto().multiply(itemPedidoDTO.getPercentualDesconto())));			
			ItemPedido itemPedido = modelMapper.map(itemPedidoDTO, ItemPedido.class);
			ItemPedido saveItemPedidoResponse = itemPedidoRepository.save(itemPedido);
			if(saveItemPedidoResponse != null) {
				produto.setQtdEstoque(produto.getQtdEstoque() - quantidade);				
				produtoRepository.save(produto);
				pedido.setValorTotal(pedido.getValorTotal().add(saveItemPedidoResponse.getValorLiquido()));				
				pedido.setStatus(itemPedidoDTO.getStatus());					
				Pedido savePedidoResponse = pedidoRepository.save(pedido);
				if(savePedidoResponse == null) {
					throw new CustomException("Erro ao atualizar o pedido no banco de dados");
				}
				
				if (savePedidoResponse.getStatus().toLowerCase().equals("fechado")) {
					
					this.enviaRelatorio(savePedidoResponse);
		    	}			
				
				return modelMapper.map(saveItemPedidoResponse, ItemPedidoDTO.class);
					
				
			}else {
				throw new CustomException("Erro ao salvar o itemPedido no banco de dados");
			}
		}
		else {
			throw new CustomException("Estoque insuficiente do produto");
		}		 
	}
	
	public ItemPedidoDTO updateItemPedidoDTO(ItemPedidoDTO itemPedidoDTO) {
	
			ItemPedido itemPedidoId = itemPedidoRepository.findById(itemPedidoDTO.getIdItemPedido()).orElse(null);
			if(itemPedidoId == null) {
				throw new CustomException("ItemPedido de Id: "+ itemPedidoDTO.getIdItemPedido()+" não encontrado");
			}
			if(itemPedidoId.getStatus().toLowerCase().equals("fechado")) {
				throw new CustomException("Pedido de Id: "+ itemPedidoId.getPedido().getIdPedido()+" já está fechado");
			}
			
			Integer quantidade = itemPedidoDTO.getQuantidade();
			Produto produto = produtoRepository.findById(itemPedidoDTO.getProduto().getIdProduto()).orElse(null);
			Pedido pedido = pedidoRepository.findById(itemPedidoDTO.getPedido().getIdPedido()).orElse(null);
			if(produto == null) {
				throw new CustomException("Produto de id: "+itemPedidoDTO.getProduto().getIdProduto()+ " não encontrado");
			}
			if(pedido == null) {
				throw new CustomException("Pedido de id: "+itemPedidoDTO.getPedido().getIdPedido()+ " não encontrado");
			}
			if(!pedido.getStatus().toLowerCase().equals("aberto")) {
				throw new CustomException("Pedido de id: "+itemPedidoDTO.getPedido().getIdPedido()+ " Não tem o status 'aberto'");
			}
			
			produto.setQtdEstoque(produto.getQtdEstoque()+itemPedidoId.getQuantidade());
			Produto prodResponse = produtoRepository.save(produto);
			if(prodResponse==null) {
				throw new CustomException("Erro ao atualizar o estoque do produto");
			}
			pedido.setValorTotal(pedido.getValorTotal().subtract(itemPedidoId.getValorLiquido()));
			Pedido pedResponse = pedidoRepository.save(pedido);
			if(pedResponse==null) {
				throw new CustomException("Erro ao atualizar o valor total do pedido");
			}			
			
			if (produto.getQtdEstoque() >= quantidade) {			
				itemPedidoDTO.setPedido(pedido);
				itemPedidoDTO.setProduto(produto);
				if(null == itemPedidoDTO.getPrecoVenda()) {
					itemPedidoDTO.setPrecoVenda(produto.getValorUnitario());
				}			
				itemPedidoDTO.setValorBruto(itemPedidoDTO.getPrecoVenda().multiply(BigDecimal.valueOf(quantidade)));
				itemPedidoDTO.setValorLiquido(itemPedidoDTO.getValorBruto().subtract(itemPedidoDTO.getValorBruto().multiply(itemPedidoDTO.getPercentualDesconto())));			
				ItemPedido itemPedido = modelMapper.map(itemPedidoDTO, ItemPedido.class);
				ItemPedido saveItemPedidoResponse = itemPedidoRepository.save(itemPedido);
				if(saveItemPedidoResponse != null) {
					produto.setQtdEstoque(produto.getQtdEstoque() - quantidade);				
					produtoRepository.save(produto);
					pedido.setValorTotal(pedido.getValorTotal().add(saveItemPedidoResponse.getValorLiquido()));				
					pedido.setStatus(itemPedidoDTO.getStatus());					
					Pedido savePedidoResponse = pedidoRepository.save(pedido);
					if(savePedidoResponse == null) {
						throw new CustomException("Erro ao atualizar o pedido no banco de dados");
					}
					
					if (savePedidoResponse.getStatus().toLowerCase().equals("fechado")) {
						
						this.enviaRelatorio(savePedidoResponse);
			    	}
					
					return modelMapper.map(saveItemPedidoResponse, ItemPedidoDTO.class);
						
					
				}else {
					throw new CustomException("Erro ao atualizar o itemPedido no banco de dados");
				}
			}
			else {
				throw new CustomException("Estoque insuficiente do produto");
			}	
		
	}
	
	   public Boolean delItemPedido(Integer id) {
		  if(itemPedidoRepository.findById(id).orElse(null)!=null) {
			  itemPedidoRepository.deleteById(id);
			  if(itemPedidoRepository.findById(id).orElse(null)==null)
				  return true;
		     else
		    	 return false;
		  }
		    else return false;
	    	  
	      }	  
	   
	   public void enviaRelatorio(Pedido pedido) {			
				
				List<ItemPedido> listaItemPedidoDTO = pedido.getItensPedidos();
				List<ItemPedidoResumidoDTO> listaRes = listaItemPedidoDTO.stream().map(x -> new ItemPedidoResumidoDTO(x)).toList();
				
				RelatorioDTO relatorioDTO = new RelatorioDTO(pedido.getIdPedido(),
						pedido.getDataPedido(), pedido.getValorTotal(),
						listaRes);					
				emailService.enviarEmail("grupo6api23.1@gmail.com", ("Pedido número: "
				+pedido.getIdPedido()),relatorioDTO.relatorio());		
	   }
}
