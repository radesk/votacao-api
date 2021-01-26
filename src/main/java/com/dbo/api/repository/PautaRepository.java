package com.dbo.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dbo.api.model.Pauta;

public interface PautaRepository extends JpaRepository<Pauta, String>{
	
	public Pauta searchByNome(String nome);


}
