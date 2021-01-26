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

import com.dbo.api.event.CreatedResourceEvent;
import com.dbo.api.model.Pauta;
import com.dbo.api.model.VotoRequest;
import com.dbo.api.repository.PautaRepository;
import com.dbo.api.service.PautaService;

@RestController
@RequestMapping("/pautas")
public class PautaResource {
	
	@Autowired
	private PautaRepository pautaRepository;
	@Autowired
	private ApplicationEventPublisher publisher;
	@Autowired
	private PautaService pautaService;

	@GetMapping
	public List<Pauta> list(){
		return pautaRepository.findAll();
	}
	
	@GetMapping("/{nome}")
	public ResponseEntity<?> searchByName(@PathVariable String nome) {
		
		
		return ResponseEntity.ok(pautaService.searchByNome(nome));
		
	}
	

	@PostMapping
	public ResponseEntity<Pauta> save(
			@RequestBody 
			@Valid
			Pauta pauta,
			HttpServletResponse response) throws Exception {
		pauta.setId(UUID.randomUUID().toString());
		pauta.setNome(pauta.getNome().toLowerCase());
		pautaService.setEncerramento(pauta);
		Pauta savedPauta = pautaRepository.save(pauta);
		
		publisher.publishEvent(new CreatedResourceEvent(this, response, savedPauta.getId()));

		return ResponseEntity.status(HttpStatus.CREATED).body(savedPauta);
	}
	
	@PostMapping("/votar")
	public ResponseEntity<?> votar(@Valid @RequestBody VotoRequest votoRequest) throws Exception{
		
		pautaService.votar(votoRequest);
		
		return ResponseEntity.ok("Voto realizado com sucesso!");
	}
	
	
	
	
}
