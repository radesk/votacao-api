package com.dbo.api.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.dbo.api.model.Usuario;
import com.dbo.api.model.request.UsuarioRequest;
import com.dbo.api.repository.UsuarioRepository;
import com.dbo.api.service.UsuarioService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@RestController
@RequestMapping("/usuarios")
public class UsuarioResource {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private UsuarioService usuarioService;

	@GetMapping
	public List<Usuario> list(){
		return usuarioRepository.findAll();
	}
	
	@GetMapping("/{cpf}")
	public ResponseEntity<?> searchById(@PathVariable String cpf) {
		
		
		return ResponseEntity.ok(usuarioService.searchByCpf(cpf));
		
	}
	
	@PutMapping("/{id}/vota")
	public ResponseEntity<Usuario> update(
			@PathVariable String id,
			@RequestBody Boolean vota
			){
		return ResponseEntity.ok(usuarioService.updateVoto(id, vota));
	}
	
	@PostMapping
	public ResponseEntity<Usuario> save(
			@RequestBody 
			@Valid
			UsuarioRequest request,
			HttpServletResponse response) throws JsonMappingException, JsonProcessingException {
		Usuario us = usuarioService.save(request, response);
		

		return ResponseEntity.status(HttpStatus.CREATED).body(us);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable String id) {
		usuarioRepository.deleteById(id);
	}
	
}
