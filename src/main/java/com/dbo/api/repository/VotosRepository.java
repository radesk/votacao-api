package com.dbo.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dbo.api.model.Votos;
import com.dbo.api.model.VotosKey;

public interface VotosRepository extends JpaRepository<Votos, VotosKey>{

}
