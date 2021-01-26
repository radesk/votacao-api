package com.dbo.api.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dbo.api.model.Votos;
import com.dbo.api.service.VotosService;

@RestController
@RequestMapping("/votos")
public class VotosResource {
	
	@Autowired
	private VotosService votosService;

	@GetMapping
	public List<Votos> list(){
		return votosService.findAll();
	}
	
}
