package com.dbo.api.resource;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dbo.api.constants.MessageUtil;
import com.dbo.api.event.CreatedResourceEvent;
import com.dbo.api.model.Pauta;
import com.dbo.api.model.request.PautaRequest;
import com.dbo.api.model.request.VotoRequest;
import com.dbo.api.repository.PautaRepository;
import com.dbo.api.service.PautaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/pautas")
@Tag(name = "Pauta", description = "Controller para realizar a criação, busca, listagem e votação de pautas")
public class PautaResource {
	
	@Autowired
	private PautaRepository pautaRepository;
	@Autowired
	private ApplicationEventPublisher publisher;
	@Autowired
	private PautaService pautaService;

	
	@Operation(summary = "Listar pautas", description = "Lista todas as pautas do sistema", tags = { "Pauta" })
    @ApiResponses(value = {
    		@ApiResponse(responseCode = "200", description = "successful operation",
    				content = @Content(array = @ArraySchema(schema = @Schema(implementation = Pauta.class))))})	
	@GetMapping
	public List<Pauta> list(){
		return pautaRepository.findAll();
	}
	
	@Operation(summary = "Buscar por nome", description = "Busca uma pauta dado um nome", tags = { "Pauta" })
    @ApiResponses(value = {
    		@ApiResponse(responseCode = "200", description = "successful operation",
    				content = @Content(array = @ArraySchema(schema = @Schema(implementation = Pauta.class)))),
    		@ApiResponse(responseCode = "404", description = "not found")
    				})	
	@GetMapping("/{nome}")
	public ResponseEntity<?> searchByName(@PathVariable String nome) {
		
		
		return ResponseEntity.ok(pautaService.searchByNome(nome));
		
	}
	

	@Operation(summary = "Criar pauta", description = "Cria uma nova pauta no sistema, data de encerramento"
			+ "e descrição são opcionais", tags = { "Pauta" })
	@ApiResponses(value = {
    		@ApiResponse(responseCode = "201", description = "created",
    				content = @Content(array = @ArraySchema(schema = @Schema(implementation = Pauta.class)))),
    		@ApiResponse(responseCode = "400", description = "bad request"),
    		@ApiResponse(responseCode = "403", description = "forbidden")
    				})
	@PostMapping(consumes = { "application/json", "application/xml" })
	public ResponseEntity<Pauta> save(
			@RequestBody 
			@Valid
			PautaRequest request,
			HttpServletResponse response) {
		Pauta savedPauta = new Pauta();
		savedPauta.setId(UUID.randomUUID().toString());
		savedPauta.setNome(request.getNome().toLowerCase());
		savedPauta.setEncerramento(request.getEncerramento());
		pautaService.setEncerramento(savedPauta);
		savedPauta = pautaRepository.save(savedPauta);
		
		publisher.publishEvent(new CreatedResourceEvent(this, response, savedPauta.getNome()));

		return ResponseEntity.status(HttpStatus.CREATED).body(savedPauta);
	}
	
	@Operation(summary = "Votar pauta", description = "Vota numa pauta dado o nome dela, cpf do usuário e o voto."
			, tags = { "Pauta" })
	@ApiResponses(value = {
    		@ApiResponse(responseCode = "201", description = "created",
    				content = @Content(array = @ArraySchema(schema = @Schema(implementation = Pauta.class)))),
    		@ApiResponse(responseCode = "400", description = "bad request"),
    		@ApiResponse(responseCode = "403", description = "forbidden")
    				})
	@PostMapping(value = "/votar", consumes = { "application/json", "application/xml" })
	public ResponseEntity<?> votar(@Valid @RequestBody VotoRequest votoRequest) {
		
		pautaService.votar(votoRequest);
		
		return ResponseEntity.ok(MessageUtil.VOTO_REALIZADO);
	}
	
	@Operation(summary = "Contabilizar votos", description = "Calcula todos os votos de uma pauta encerrada", tags = { "Pauta" })
    @ApiResponses(value = {
    		@ApiResponse(responseCode = "200", description = "successful operation",
    				content = @Content(array = @ArraySchema(schema = @Schema(implementation = Pauta.class)))),
    		@ApiResponse(responseCode = "404", description = "not found")
    				})
	@GetMapping("/contabilizarVotos/{nomePauta}")
	public ResponseEntity<?> contabilizarVotos(@PathVariable String nomePauta){
		
		String resultado = pautaService.contabilizarVotos(nomePauta);
		
		return ResponseEntity.ok().body(resultado);
	}
	
}
