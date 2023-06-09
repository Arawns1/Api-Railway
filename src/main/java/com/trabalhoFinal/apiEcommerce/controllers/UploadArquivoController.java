package com.trabalhoFinal.apiEcommerce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.trabalhoFinal.apiEcommerce.dto.MessageDTO;
import com.trabalhoFinal.apiEcommerce.dto.UploadArquivoDTO;
import com.trabalhoFinal.apiEcommerce.entities.UploadArquivo;
import com.trabalhoFinal.apiEcommerce.services.ProdutoService;
import com.trabalhoFinal.apiEcommerce.services.UploadArquivoService;

@RestController
@RequestMapping("/upload")
public class UploadArquivoController {

	@Autowired
	UploadArquivoService uploadArquivoService;

	@Autowired
	ProdutoService produtoService;

	@PreAuthorize("hasAnyRole('ADMIN', 'MODERATOR')")
	@PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE })
	public ResponseEntity<UploadArquivoDTO> uploadArquivo(@RequestParam("file") MultipartFile file,
			@RequestParam("url") String url) {

		return new ResponseEntity<>(uploadArquivoService.armazenaArquivo(file, url), HttpStatus.CREATED);
	}

	@GetMapping("/view/{id}")
	public ResponseEntity<?> getArquivo(@PathVariable Integer id) {
		UploadArquivo arquivo = uploadArquivoService.getFile(id);

		if (arquivo != null) {
			return ResponseEntity.ok()
					.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + arquivo.getNome() + "\"")
					.contentType(MediaType.valueOf(arquivo.getTipoArquivo())).body(arquivo.getData());
		} else
			return new ResponseEntity<>(new MessageDTO("Imagem não encontrada"), HttpStatus.NOT_FOUND);
	}

	@PreAuthorize("hasAnyRole('ADMIN', 'MODERATOR')")
	@DeleteMapping("/{id}")
	public ResponseEntity<MessageDTO> delArquivo(@PathVariable Integer id) {
		return new ResponseEntity<>(uploadArquivoService.delFile(id), HttpStatus.OK);

	}
}
