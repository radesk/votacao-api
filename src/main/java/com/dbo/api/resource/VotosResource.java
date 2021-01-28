package com.dbo.api.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dbo.api.model.Votos;
import com.dbo.api.service.VotosService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/votos")
@Tag(name = "Votos", description = "Controller responsável pela listagem geral de votos no sistema")
public class VotosResource {
	
	@Autowired
	private VotosService votosService;

	@GetMapping
	@Operation(summary = "Listar Votos", description = "Lista todos os votos do sistema", tags = { "Votos" })
    @ApiResponses(value = {
    		@ApiResponse(responseCode = "200", description = "successful operation",
    				content = @Content(array = @ArraySchema(schema = @Schema(implementation = Votos.class))))})	
	public List<Votos> list(){
		return votosService.findAll();
	}
	
}