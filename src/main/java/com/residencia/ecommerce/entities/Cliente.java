package com.residencia.ecommerce.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@JsonIdentityInfo(
		scope = Cliente.class,
		generator = ObjectIdGenerators.PropertyGenerator.class,
		property = "idCliente"
		)
@Entity
@Table(name = "cliente")
public class Cliente {
	@Id // indica que esse atributo é chave primaria (obrigatorio)
	@GeneratedValue(strategy = GenerationType.IDENTITY) // indica se o java ou o banco de dados será responsavel pelo autoincremento
	@Column(name = "id_cliente")
	private Integer idCliente;
	
	
	@Column(name = "cpf", unique = true)
	private String cpf;
	
	@Column(name = "nome_completo")
	private String nome;
	
	@Column(name = "email", unique = true)
	private String email;
	
	@Column(name = "telefone", unique = true)
	private String telefone;
	
	@Column(name = "data_nascimento")
	private Date dataNascimento;
	
	@Column(name = "user_name", unique = true)
	private String username;
	
	@Column(name = "password")
	private String password;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "cliente_roles", joinColumns = @JoinColumn(name = "id_cliente"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();

	
	
	//cardinalidade 1:1
	@OneToOne
	@JoinColumn(name = "id_endereco", 
			referencedColumnName = "id_endereco")
	private Endereco endereco; // relacionando a classe endereço com o cliente
	
	//cardinalidade 1:N
	@OneToMany(mappedBy = "cliente") //linkando com o "cliente" criado no pedido
	private List<Pedido> pedido; // 1 cliente pode ter N pedidos

	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer numeroIdCliente) {
		this.idCliente = numeroIdCliente;
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

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public List<Pedido> getPedido() {
		return pedido;
	}

	public void setPedido(List<Pedido> pedido) {
		this.pedido = pedido;
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

	@Override
	public String toString() {
		return "Cliente [numeroIdCliente=" + idCliente + ", cpf=" + cpf + ", nome=" + nome + ", email=" + email
				+ ", telefone=" + telefone + ", dataNascimento=" + dataNascimento + ", endereco=" + endereco
				+ ", pedido=" + pedido + "]";
	}
	
}
