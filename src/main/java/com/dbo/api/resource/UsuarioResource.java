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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/usuarios")
@Tag(name = "Usuario", description = "Controller para realizar o CRUD de "
		+ "usuários como também a permissão do mesmo para votar em uma determinada pauta")
public class UsuarioResource {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private UsuarioService usuarioService;

	
	@Operation(summary = "Listar Usuarios", description = "Lista todos os usuários do sistema", tags = { "Usuario" })
    @ApiResponses(value = {
    		@ApiResponse(responseCode = "200", description = "successful operation",
    				content = @Content(array = @ArraySchema(schema = @Schema(implementation = Usuario.class))))})	
	@GetMapping
	public List<Usuario> list(){
		return usuarioRepository.findAll();
	}
	
	@Operation(summary = "Buscar por cpf", description = "Busca um usuário dado um cpf", tags = { "Usuario" })
    @ApiResponses(value = {
    		@ApiResponse(responseCode = "200", description = "successful operation",
    				content = @Content(array = @ArraySchema(schema = @Schema(implementation = Usuario.class)))),
    		@ApiResponse(responseCode = "404", description = "not found")
    				})	
	@GetMapping("/{cpf}")
	public ResponseEntity<?> searchById(@PathVariable String cpf) {
		
		
		return ResponseEntity.ok(usuarioService.searchByCpf(cpf));
		
	}
	
	@Operation(summary = "Alterar permissão", description = "Altera a permissão do usuário para votar, dado um id", tags = { "Usuario" })
	@ApiResponses(value = {
    		@ApiResponse(responseCode = "200", description = "successful operation",
    				content = @Content(array = @ArraySchema(schema = @Schema(implementation = Usuario.class)))),
    		@ApiResponse(responseCode = "404", description = "not found"),
    		@ApiResponse(responseCode = "400", description = "bad request")
    				})
	@PutMapping(value = "/{id}/vota", consumes = { "application/json", "application/xml" })
	public ResponseEntity<Usuario> update(
			@PathVariable String id,
			@RequestBody(required = true) Boolean vota
			){
		return ResponseEntity.ok(usuarioService.updateVoto(id, vota));
	}
	
	@Operation(summary = "Salvar usuário", description = "Cria um novo usuário no sistema, "
			+ "caso o campo vota não seja informado, faz validação do cpf por api externa.", tags = { "Usuario" })
	@ApiResponses(value = {
    		@ApiResponse(responseCode = "201", description = "created",
    				content = @Content(array = @ArraySchema(schema = @Schema(implementation = Usuario.class)))),
    		@ApiResponse(responseCode = "400", description = "bad request"),
    		@ApiResponse(responseCode = "403", description = "forbidden")

    				})
	@PostMapping(consumes = { "application/json", "application/xml" })
	public ResponseEntity<Usuario> save(
			@RequestBody(required = true) 
			@Valid
			UsuarioRequest request,
			HttpServletResponse response) throws JsonMappingException, JsonProcessingException {
		Usuario us = usuarioService.save(request, response);
		

		return ResponseEntity.status(HttpStatus.CREATED).body(us);
	}
	
	@Operation(summary = "Deletar usuário", description = "Apaga um usuário do sistema dado um id ", tags = { "Usuario" })
	@ApiResponses(value = {
    		@ApiResponse(responseCode = "204", description = "successful operation"),
    		@ApiResponse(responseCode = "404", description = "not found"),
    				})
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable String id) {
		usuarioRepository.deleteById(id);
	}
	
}
