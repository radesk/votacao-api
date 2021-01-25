package com.dbo.api.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dbo.api.event.CreatedResourceEvent;
import com.dbo.api.model.Votos;
import com.dbo.api.repository.VotosRepository;

@RestController
@RequestMapping("/votos")
public class VotosResource {
	
	@Autowired
	private VotosRepository votosRepository;
	@Autowired
	private ApplicationEventPublisher publisher;

	@GetMapping
	public List<Votos> list(){
		return votosRepository.findAll();
	}
	
	@PostMapping
	public ResponseEntity<Votos> save(
			@RequestBody 
			@Valid
			Votos votos,
			HttpServletResponse response) {
		Votos savedVotos = votosRepository.save(votos);
		
		publisher.publishEvent(new CreatedResourceEvent(this, response, savedVotos.getIdVoto().getIdPauta()));

		return ResponseEntity.status(HttpStatus.CREATED).body(savedVotos);
	}
	
}
