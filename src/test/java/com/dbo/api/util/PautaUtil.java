package com.dbo.api.util;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import com.dbo.api.model.Pauta;
import com.dbo.api.model.Votos;

public class PautaUtil {
	
	private static final String ID = "dc3d45bf-186d-425b-a6c7-580a2c761eb1";
	private static final String NOME = "Teste";
	private static final String DESCRICAO = "Pauta de teste para iniciar o projeto";
	private static final LocalDateTime ENC_VALIDO = LocalDateTime.parse("2021-02-01T00:00:01");
	private static final List<Votos> LISTA_VOTOS = VotosUtil.buildList();
	
	public static Pauta buildPauta() {
		return Pauta.builder()
				.descricao(DESCRICAO)
				.encerramento(ENC_VALIDO)
				.id(ID)
				.nome(NOME)
				.votos(LISTA_VOTOS)
				.build();
	}

	public static List<Pauta> buildList() {
		return Collections.singletonList(buildPauta());
	}

}
