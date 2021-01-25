package com.dbo.api.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.dbo.api.model.Pauta;
import com.dbo.api.repository.PautaRepository;

@Service
public class PautaService {
	@Autowired
	private PautaRepository pr;
	
	public Pauta searchById(String id) {
		Optional<Pauta> savedPauta = pr.findById(id);
		if (savedPauta.isPresent())
			return savedPauta.get();
		else
			throw new EmptyResultDataAccessException(1);
	}

	public void setEncerramento(Pauta pauta) throws Exception {
		if (pauta.getEncerramento() == null) pauta.setEncerramento(LocalDateTime.now().plusMinutes(1));
		else {
			if (pauta.getEncerramento().isBefore(LocalDateTime.now())) {
				throw new Exception("Encerramento não pode ser antes do momento atual");
			}
		}
	}

}
