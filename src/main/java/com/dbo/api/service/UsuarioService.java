package com.dbo.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.dbo.api.model.Usuario;
import com.dbo.api.repository.UsuarioRepository;

@Service
public class UsuarioService {
	@Autowired
	private UsuarioRepository ur;
	
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

}
