package com.residencia.ecommerce.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.residencia.ecommerce.dto.ProdutoDTO;
import com.residencia.ecommerce.services.ProdutoService;

import io.jsonwebtoken.io.IOException;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
	
	@Autowired
	ProdutoService produtoService;
	
	@GetMapping
    public ResponseEntity<List<ProdutoDTO>> getAllProdutosDTO() {
        return new ResponseEntity<>(produtoService.getAllProdutosDTO(),
                HttpStatus.OK);
        //ResponseEntity manipula o retorno
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ProdutoDTO> getProdutoById(@PathVariable Integer id) {
        ProdutoDTO produtoDtoResponse = produtoService.getProdutoDtoById(id);
        if(null == produtoDtoResponse)
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(produtoDtoResponse, HttpStatus.OK);
    }
    
    @GetMapping("/{id}/img")
    public ResponseEntity<?> getProdutoImg(@PathVariable Integer id) {
        byte[] img = produtoService.getProdutoImg(id);
        if(null == img)
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        else
            return ResponseEntity.status(HttpStatus.OK)
            		.contentType(MediaType.valueOf("image/jpg"))
            		.body(img);
    }
    
    @PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<ProdutoDTO> saveProdutoDTO(@RequestPart("produtoDTO") String produtoDTO,
   		@RequestPart("source") MultipartFile file) throws IOException{
    		
    		ProdutoDTO novoProdutoDTO = produtoService.saveProdutoDTO(produtoDTO, file);
    			if(null == novoProdutoDTO)
    				return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    			else
    				return new ResponseEntity<>(novoProdutoDTO, HttpStatus.CREATED);
    }
    
    @PutMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<ProdutoDTO> updateProduto(@RequestPart("produtoDTO") String produtoDTO,
       		@RequestPart("source") MultipartFile file) throws IOException {
    	ProdutoDTO novoProdutoDTO = produtoService.updateProdutoDTO(produtoDTO, file);
    	if(novoProdutoDTO!= null) {
            return new ResponseEntity<> (novoProdutoDTO,
                    HttpStatus.OK);
        }
        else {
            return new ResponseEntity<> ( null,
                    HttpStatus.NOT_FOUND);
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delProduto(@PathVariable Integer id) {
        Boolean resp = produtoService.delProduto(id);
        if(resp)
        	return new ResponseEntity<>(resp,HttpStatus.OK);
        else
        	return new ResponseEntity<>(resp,HttpStatus.NOT_MODIFIED);
    }

}




