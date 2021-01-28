package com.dbo.api.model.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.dbo.api.model.Voto;

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
