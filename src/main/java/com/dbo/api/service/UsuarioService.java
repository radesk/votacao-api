package com.dbo.api.service;

import java.util.Optional;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.dbo.api.event.CreatedResourceEvent;
import com.dbo.api.integration.Terceiros;
import com.dbo.api.model.Usuario;
import com.dbo.api.model.request.UsuarioRequest;
import com.dbo.api.repository.UsuarioRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@Service
public class UsuarioService {
	@Autowired
	private UsuarioRepository ur;
	@Autowired
	private ApplicationEventPublisher publisher;
	@Autowired
	private Terceiros terceiros;
	
	public Usuario updateVoto(String id, boolean vota) {
		Usuario savedUsuario = searchById(id);
		savedUsuario.setVota(vota);
		ur.save(savedUsuario);
		return savedUsuario;
	}

	public Usuario searchById(String id) {
		Optional<Usuario> savedUsuario = ur.findById(id);
		if (savedUsuario.isPresent())
			return savedUsuario.get();
		else
			throw new EmptyResultDataAccessException(1);
	}

	public Usuario searchByCpf(String cpf) {
		Optional<Usuario> savedUsuario = ur.searchByCpf(cpf);
		if (savedUsuario.isPresent())
			return savedUsuario.get();
		else
			throw new EmptyResultDataAccessException(1);
	}

	public Usuario save(@Valid UsuarioRequest request, HttpServletResponse response) throws JsonMappingException, JsonProcessingException {
		if (request.getVota() == null) request.setVota(terceiros.validaCpf(request.getCpf()));
		Usuario us = new Usuario();
		us.setId(UUID.randomUUID().toString());
		us.setCpf(request.getCpf());
		us.setVota(request.getVota());
		us = ur.save(us);
		publisher.publishEvent(new CreatedResourceEvent(this, response, us.getId()));
		
		return us;
	}

}
