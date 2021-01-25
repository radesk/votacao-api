package com.dbo.api.model;

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
@Table(name = "usuario")
public class Usuario {
	
	@Id
	private String id;
	
	@NotBlank
	@Size(min = 11, max = 11)
	private String cpf;
	private Boolean vota;

	@JsonIgnore
	@OneToMany(
	        mappedBy = "idUsuario",
	        cascade = CascadeType.ALL,
	        orphanRemoval = true
	    )
	private List<Votos> votos;
}
