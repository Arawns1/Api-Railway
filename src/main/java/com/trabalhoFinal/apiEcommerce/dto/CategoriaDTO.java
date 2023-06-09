package com.trabalhoFinal.apiEcommerce.dto;

import java.util.List;

public class CategoriaDTO {

	private String nome;
	private String descricao;
	private List<ProdutoCatDTO> produtos;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public List<ProdutoCatDTO> getProdutos() {
		return produtos;
	}
	public void setProdutos(List<ProdutoCatDTO> produtos) {
		this.produtos = produtos;
	}
}
