package com.dbo.api.util;

import com.dbo.api.model.VotosKey;

public class VotosKeyUtil {
	
	private static final String ID_USUARIO = "5d41e95f-480a-410b-a88a-3cdf96b0467d";
	private static final String ID_PAUTA = "dc3d45bf-186d-425b-a6c7-580a2c761eb1";
	
	public static VotosKey buildKey() {
		return VotosKey.builder()
				.idPauta(ID_PAUTA)
				.idUsuario(ID_USUARIO)
				.build();
	}

}
