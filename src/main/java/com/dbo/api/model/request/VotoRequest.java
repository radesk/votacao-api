package com.dbo.api.model.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.dbo.api.model.Voto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VotoRequest {
	
	@NotBlank
	private String cpf;

	@NotBlank
	private String nomePauta;
	
	@NotNull(message = "Voto não pode ficar em branco ou nulo")
	private Voto voto;

}
