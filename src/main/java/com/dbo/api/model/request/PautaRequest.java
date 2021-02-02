package com.dbo.api.model.request;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PautaRequest {
	
	@NotBlank
	@Size(max = 50)
	private String nome;
	
	@Size(max = 50)
	private String descricao;
	
	private LocalDateTime encerramento;

}
