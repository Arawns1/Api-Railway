package com.trabalhoFinal.apiEcommerce.services;

import java.io.IOException;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.trabalhoFinal.apiEcommerce.dto.MessageDTO;
import com.trabalhoFinal.apiEcommerce.dto.UploadArquivoDTO;
import com.trabalhoFinal.apiEcommerce.entities.UploadArquivo;
import com.trabalhoFinal.apiEcommerce.exceptions.UploadArquivoException;
import com.trabalhoFinal.apiEcommerce.exceptions.UploadArquivoNotFoundException;
import com.trabalhoFinal.apiEcommerce.repositories.UploadArquivoRepository;

@Service
public class UploadArquivoService {

	@Autowired
	UploadArquivoRepository uploadRepository;

	public UploadArquivoDTO armazenaArquivo(MultipartFile file, String url) {
		String clearFileName = StringUtils.cleanPath(file.getOriginalFilename());
		
		// url é o endereço para imagem que vai aparecer no email; não é obrigatória
		// se uma não for enviada uma url, a seguinte será enviada:
		if (url.isBlank())
			url = "https://i.ibb.co/XxM2QFs/notfound.png";

		try {

			if (clearFileName.contains("..")) {
				throw new UploadArquivoException("Nome de arquivo inválido: " + clearFileName);
			}

			UploadArquivo arquivo = new UploadArquivo(clearFileName, file.getContentType(), url, file.getBytes());
			uploadRepository.save(arquivo);
			return new UploadArquivoDTO(clearFileName, arquivo.getId_imagem(), file.getContentType(), file.getSize(),
					url);

		} catch (IOException ex) {
			throw new UploadArquivoException("Ocorreu um erro ao armazenar o arquivo" + clearFileName, ex);
		}
	}

	public UploadArquivo getFile(Integer id) {
		Optional<UploadArquivo> arquivoGet = uploadRepository.findById(id);

		if (arquivoGet != null) {
			ModelMapper modelMapper = new ModelMapper();
			return modelMapper.map(arquivoGet, UploadArquivo.class);
		} else {
			return null;
		}
	}

	public MessageDTO delFile(Integer id) {
		uploadRepository.findById(id).orElseThrow(() -> new UploadArquivoNotFoundException(id));
		uploadRepository.deleteById(id);
		return new MessageDTO("Arquivo deletado com sucesso!");

	}

	public UploadArquivoService() {
	}
}
