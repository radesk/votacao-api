package com.dbo.api.util.request;

import com.dbo.api.model.Voto;
import com.dbo.api.model.request.VotoRequest;

public class VotoRequestUtil {
	
	private static final String CPF_VALIDO = "67203243060";
	private static final Voto VOTO = Voto.Sim;
	private static final String NOME = "Teste";
	
	public static VotoRequest buildRequest() {
		return VotoRequest.builder()
				.cpf(CPF_VALIDO)
				.nomePauta(NOME)
				.voto(VOTO)
				.build();
	} 

}
