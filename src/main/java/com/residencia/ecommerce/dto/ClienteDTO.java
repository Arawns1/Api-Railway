package com.residencia.ecommerce.dto;

import java.util.Date;
import java.util.List;
import java.util.Set;

import com.residencia.ecommerce.entities.Cliente;
import com.residencia.ecommerce.entities.Endereco;
import com.residencia.ecommerce.entities.Pedido;
import com.residencia.ecommerce.entities.Role;


public class ClienteDTO {	
	
	private Integer IdCliente;
	
	private String cpf;
	
	private String nome;
	
	private String email;
	
	private String telefone;
	
	private Date dataNascimento;
	
	private String username;
	
	private String password;
	
	private Set<Role> roles;
	
	private Set<String> strRoles;

	private Endereco endereco; // relacionando a classe endereço com o cliente

	private List<Pedido> pedidos; // 1 cliente pode ter N pedidos

	public ClienteDTO(Cliente cliente) {
		super();
		this.IdCliente = cliente.getIdCliente();
		this.cpf = cliente.getCpf();
		this.nome = cliente.getNome();
		this.email = cliente.getEmail();
		this.telefone = cliente.getTelefone();
		this.dataNascimento = cliente.getDataNascimento();
		this.username = cliente.getUsername();
		this.password = cliente.getPassword();
		this.roles = cliente.getRoles();		
		this.pedidos = cliente.getPedido();		
	}
	

	public ClienteDTO() {
		super();
		
	}


	public ClienteDTO(Integer idCliente, String cpf, String nome, String email, String telefone, Date dataNascimento,
			String username, String password, Set<Role> roles, Set<String> strRoles, Endereco endereco,
			List<Pedido> pedidos) {
		super();
		IdCliente = idCliente;
		this.cpf = cpf;
		this.nome = nome;
		this.email = email;
		this.telefone = telefone;
		this.dataNascimento = dataNascimento;
		this.username = username;
		this.password = password;
		this.roles = roles;
		this.strRoles = strRoles;
		this.endereco = endereco;
		this.pedidos = pedidos;
	}


	public Integer getIdCliente() {
		return IdCliente;
	}


	public void setIdCliente(Integer idCliente) {
		IdCliente = idCliente;
	}


	public String getCpf() {
		return cpf;
	}


	public void setCpf(String cpf) {
		this.cpf = cpf;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getTelefone() {
		return telefone;
	}


	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}


	public Date getDataNascimento() {
		return dataNascimento;
	}


	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public Set<Role> getRoles() {
		return roles;
	}


	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}


	public Set<String> getStrRoles() {
		return strRoles;
	}


	public void setStrRoles(Set<String> strRoles) {
		this.strRoles = strRoles;
	}


	public Endereco getEndereco() {
		return endereco;
	}


	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}


	public List<Pedido> getPedidos() {
		return pedidos;
	}


	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}	
		
}
