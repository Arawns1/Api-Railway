package com.trabalhoFinal.apiEcommerce.dto;

import java.util.List;

public class ClienteDTO {

	private String email;
	private String nome_completo;
	private String telefone;
	private EnderecoClienteDTO endereco;
	private List<PedidoClienteDTO> pedidos;
	
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNome_completo() {
		return nome_completo;
	}
	public void setNome_completo(String nome_completo) {
		this.nome_completo = nome_completo;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public List<PedidoClienteDTO> getPedidos() {
		return pedidos;
	}
	public void setPedidos(List<PedidoClienteDTO> pedidos) {
		this.pedidos = pedidos;
	}
	public EnderecoClienteDTO getEndereco() {
		return endereco;
	}
	public void setEndereco(EnderecoClienteDTO endereco) {
		this.endereco = endereco;
	}
}
