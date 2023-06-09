package com.trabalhoFinal.apiEcommerce.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trabalhoFinal.apiEcommerce.dto.MessageDTO;
import com.trabalhoFinal.apiEcommerce.entities.ItemPedido;
import com.trabalhoFinal.apiEcommerce.entities.Pedido;
import com.trabalhoFinal.apiEcommerce.entities.Produto;
import com.trabalhoFinal.apiEcommerce.exceptions.ItemPedidoNotFoundException;
import com.trabalhoFinal.apiEcommerce.repositories.ItemPedidoRepository;

@Service
public class ItemPedidoService {

	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

	@Autowired
	private PedidoService pedidoService;
	
	@Autowired
	private ProdutoService produtoService;

	public List<ItemPedido> getAllItemPedidos() {
		return itemPedidoRepository.findAll();
	}

	public ItemPedido getItemPedidoById(Integer id) {
		return itemPedidoRepository.findById(id).orElseThrow(() -> new ItemPedidoNotFoundException(id));
	}

	public Boolean saveItemPedido(ItemPedido itemPedido) {
		
		Produto produto = produtoService.getProdutoById(itemPedido.getProduto().getId_produto());
		Integer qntProduto = produto.getQtd_estoque();
		
		if(itemPedido.getQuantidade() > qntProduto)
			return false;
		
		itemPedido.setValor_bruto(itemPedido.getPreco_venda() * itemPedido.getQuantidade());
		itemPedido.setValor_liquido(itemPedido.getValor_bruto()
				- (itemPedido.getValor_bruto() * (itemPedido.getPercentual_desconto() / 100)));
		
		
		atualizaValorTotal(itemPedido, 0);
		atualizaEstoque(itemPedido, 0);
		itemPedidoRepository.save(itemPedido);
		
		return true;
	}

	public ItemPedido updateItemPedido(ItemPedido itemPedido, Integer id) {
		return itemPedidoRepository.save(itemPedido);
	}

	public MessageDTO delItemPedido(Integer id) {
		ItemPedido itemPedido = itemPedidoRepository.findById(id)
				.orElseThrow(() -> new ItemPedidoNotFoundException(id));

		itemPedidoRepository.deleteById(id);
		atualizaValorTotal(itemPedido, 1);
		atualizaEstoque(itemPedido, 1);
		return new MessageDTO("ItemPedido Deletado com sucesso!");

	}

	// atualiza o valor total do pedido toda vez que um novo ItemPedido é salvo ou deletado
	public void atualizaValorTotal(ItemPedido itemPedido, Integer r) {
		// r = 0 | saveItemPedido
		// r = 1 | delItemPedido

		Pedido pedidoAtualizado = pedidoService.getPedidoById(itemPedido.getPedido().getId_pedido());
		pedidoAtualizado.setValor_total(itemPedido.getValor_liquido(), r);
		pedidoService.updatePedido(pedidoAtualizado);
	}
	
	//atualiza a quantidade em estoque do produto toda vez que um novo ItemPedido é salvo ou deletado
	public void atualizaEstoque(ItemPedido itemPedido, Integer r) {
		// r = 0 | saveItemPedido
		// r = 1 | delItemPedido
		
		Produto produtoPedido = produtoService.getProdutoById(itemPedido.getProduto().getId_produto());
		Integer qntdPedido = itemPedido.getQuantidade();
		produtoPedido.atualizaEstoque(r, qntdPedido);
		produtoService.updateProduto(produtoPedido);
	}
}
