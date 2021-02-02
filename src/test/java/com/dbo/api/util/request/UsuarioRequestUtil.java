package com.dbo.api.util.request;

import java.time.LocalDateTime;

import com.dbo.api.model.request.UsuarioRequest;

public class UsuarioRequestUtil {
	
	private static final String CPF_VALIDO = "67203243060";
	private static final Boolean VOTA = true;
	
	
	public static UsuarioRequest buildRequest() {
		return UsuarioRequest.builder()
				.cpf(CPF_VALIDO)
				.vota(VOTA)
				.build();
	}

}
