package com.dbo.api.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Entity
@Table(name = "usuario")
public class Usuario {
	
	@Id
	private String id;
	
	@NotBlank
	@Size(min = 9, max = 9)
	private String cpf;
	private Boolean vota;

}
