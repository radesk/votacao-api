package com.dbo.api.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class VotoRequest {
	
	@NotBlank
	private String cpf;

	@NotBlank
	private String nomePauta;
	
	@NotNull
	private Voto voto;

}
