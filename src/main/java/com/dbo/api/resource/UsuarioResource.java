package com.dbo.api.resource;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.dbo.api.model.Usuario;
import com.dbo.api.repository.UsuarioRepository;

@RestController
@RequestMapping("/usuarios")
public class UsuarioResource {
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@GetMapping
	public List<Usuario> list(){
		return usuarioRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> searchById(@PathVariable String id) {
		
		Optional<Usuario> usuario = usuarioRepository.findById(id);
		
		return usuario.isPresent()? ResponseEntity.ok(usuario): ResponseEntity.notFound().build();
		
	}
	
	@PostMapping
	public ResponseEntity<Usuario> save(
			@RequestBody 
			@Valid
			Usuario usuario,
			HttpServletResponse response) {
		usuario.setId(UUID.randomUUID().toString());
		Usuario savedUsuario = usuarioRepository.save(usuario);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
		.buildAndExpand(savedUsuario.getId()).toUri();
		
		response.setHeader("Location", uri.toASCIIString());

		return ResponseEntity.created(uri).body(savedUsuario);
	}
	
}
