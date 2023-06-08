package com.residencia.ecommerce.dto;

import java.util.List;

import com.residencia.ecommerce.entities.Categoria;
import com.residencia.ecommerce.entities.Produto;


public class CategoriaDTO {

private Integer idCategoria;
	
	
private String nome;
	

private String descricao;
	

private List<Produto> produtos;


public CategoriaDTO() {
	super();

}
public CategoriaDTO(Categoria categoria) {
	super();
	this.idCategoria = categoria.getIdCategoria();
	this.nome = categoria.getNome();
	this.descricao = categoria.getDescricao();
	this.produtos = categoria.getProdutos();
	
}

public CategoriaDTO(Integer idCategoria, String nome, String descricao, List<Produto> produtos) {
	super();
	this.idCategoria = idCategoria;
	this.nome = nome;
	this.descricao = descricao;
	this.produtos = produtos;
}


public Integer getIdCategoria() {
	return idCategoria;
}


public void setIdCategoria(Integer idCategoria) {
	this.idCategoria = idCategoria;
}


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


public List<Produto> getProdutos() {
	return produtos;
}


public void setProdutos(List<Produto> produtos) {
	this.produtos = produtos;
} 
	
}
