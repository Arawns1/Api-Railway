package com.trabalhoFinal.apiEcommerce.dto;

public class UploadArquivoDTO {

	private String fileName;
	private Integer fileId;
	private String fileType;
	private long size;
	private String url_imagem;
	
	

	public UploadArquivoDTO(String fileName, Integer fileId, String fileType, long size, String url_imagem) {
		super();
		this.fileName = fileName;
		this.fileId = fileId;
		this.fileType = fileType;
		this.size = size;
		this.url_imagem = url_imagem;
	}

	public String getUrl_imagem() {
		return url_imagem;
	}

	public void setUrl_imagem(String url_imagem) {
		this.url_imagem = url_imagem;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Integer getFileId() {
		return fileId;
	}

	public void setFileId(Integer fileId) {
		this.fileId = fileId;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}
}
