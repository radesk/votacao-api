package com.dbo.api.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "pauta")
public class Pauta {
	
	@Id
	private String id;
	
	@NotBlank
	@Size(max = 50)
	private String nome;
	
	@Size(max = 50)
	private String descricao;
	
	private LocalDateTime encerramento;
	
	@JsonIgnore
	@OneToMany(
	        mappedBy = "idPauta",
	        cascade = CascadeType.ALL,
	        orphanRemoval = true
	    )
	private List<Votos> votos;

}

