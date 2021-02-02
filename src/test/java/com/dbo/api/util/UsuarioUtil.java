package com.dbo.api.util;

import java.util.Collections;
import java.util.List;

import com.dbo.api.model.Usuario;
import com.dbo.api.model.Votos;

public class UsuarioUtil {
	
	private static final String ID = "5d41e95f-480a-410b-a88a-3cdf96b0467d";
	private static final String CPF_VALIDO = "67203243060";
	private static final Boolean VOTA = true;
	private static final List<Votos> VOTOS = VotosUtil.buildList();
	
	
	public static Usuario buildUsuario() {
		return Usuario.builder()
				.cpf(CPF_VALIDO)
				.id(ID)
				.vota(VOTA)
				.votos(VOTOS)
				.build();
	}


	public static List<Usuario> buildList() {
		return Collections.singletonList(buildUsuario());
	}

}
