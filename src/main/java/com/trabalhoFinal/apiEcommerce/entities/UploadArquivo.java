package com.trabalhoFinal.apiEcommerce.entities;

import java.sql.Types;

import org.hibernate.annotations.JdbcTypeCode;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToOne;

@JsonIdentityInfo(
		generator = ObjectIdGenerators.PropertyGenerator.class,
		property = "id_imagem",
		scope =  UploadArquivo.class
		) 
@Entity
public class UploadArquivo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_imagem;
	
	@Column(name="nome_imagem")
	private String nome;
	
	@Column(name="tipo_arquivo")
	private String tipoArquivo;
	
	@OneToOne(mappedBy = "arquivo")
	private Produto produto;

	@Column(name="url_imagem")
	private String url_imagem;
	
	@Lob
	@JdbcTypeCode(Types.BINARY)
	@JsonIgnore
	private byte[] data;
	
	

	public UploadArquivo() {
	}


	public UploadArquivo(String nome, String tipoArquivo, String url_imagem, byte[] data) {
		super();
		this.nome = nome;
		this.tipoArquivo = tipoArquivo;
		this.url_imagem = url_imagem;
		this.data = data;
	}

	public String getUrl_imagem() {
		return url_imagem;
	}
	public void setUrl_imagem(String url_imagem) {
		this.url_imagem = url_imagem;
	}
	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Integer getId_imagem() {
		return id_imagem;
	}

	public void setId_imagem(Integer id_imagem) {
		this.id_imagem = id_imagem;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTipoArquivo() {
		return tipoArquivo;
	}

	public void setTipoArquivo(String tipoArquivo) {
		this.tipoArquivo = tipoArquivo;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}
}
