package com.dbo.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dbo.api.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, String>{

	public Optional<Usuario> searchByCpf(String cpf);

}
