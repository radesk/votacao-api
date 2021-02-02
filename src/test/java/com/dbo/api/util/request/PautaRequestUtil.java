package com.dbo.api.util.request;

import java.time.LocalDateTime;

import com.dbo.api.model.request.PautaRequest;

public class PautaRequestUtil {
	
	private static final String NOME = "Teste";
	private static final String DESCRICAO = "Pauta de teste para iniciar o projeto";
	private static final LocalDateTime ENC_VALIDO = LocalDateTime.parse("2021-02-01T00:00:01");
	
	public static PautaRequest buildRequest() {
		return PautaRequest.builder()
				.descricao(DESCRICAO)
				.encerramento(ENC_VALIDO)
				.nome(NOME)
				.build();
	}

}
